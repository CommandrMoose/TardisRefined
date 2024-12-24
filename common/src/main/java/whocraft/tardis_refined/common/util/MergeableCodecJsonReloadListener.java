package whocraft.tardis_refined.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.NetworkManager;

import java.io.Reader;
import java.util.*;
import java.util.function.Function;

/**
 * Reusable codec based SimplePreparableReloadListener
 * <br> Loads individual JSON files from a specified folder in a datapack, loads them into a registry then syncs the data from server to client via a sync packet.
 * <br> This is used to merge multiple JSON files' data togethor, smilar to vanilla's tag files, datapacks can define tags with the same modid:name and merge all entries into the same list
 *
 * @param <RAW>       - The object that the codec is parsing json into
 * @param <PROCESSED> - The type of the object after merging the parsed objects. Can be the same type as RAW
 */
public class MergeableCodecJsonReloadListener<RAW, PROCESSED> extends SimplePreparableReloadListener<Map<ResourceLocation, PROCESSED>> {

    protected final Codec<RAW> codec; // Make the codec protected access because some implementations may require extra logic to be added when we are decoding entries
    protected final String folderName;
    protected final String EXTENSION_NAME = ".json";
    protected final int EXTENSION_LENGTH = EXTENSION_NAME.length();

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/MergeableCodecJsonReloadListener");

    protected final Function<List<RAW>, PROCESSED> merger;

    /**
     * The raw data that we parsed from json last time resources were reloaded
     **/
    protected Map<ResourceLocation, PROCESSED> data = new HashMap<>();

    /**
     * DO NOT USE THIS CONSTRUCTOR, use the factory method because this needs to use platform-specific logic.
     * <br> The default implementation does not send the sync packet, hence the need for a factory method.
     * <br> Creates a reload listener with a standard gson parser.
     *
     * @param folderName The name of the data folder that we will load from, vanilla folderNames are "recipes", "loot_tables", etc.
     *                   <br> Jsons will be read from data/all_modids/folderName/all_jsons
     *                   <br> folderName can include subfolders, e.g. "modid/folder"
     * @param codec      A codec to deserialize the json into your T, see javadocs above class
     */
    protected MergeableCodecJsonReloadListener(String folderName, Codec<RAW> codec, final Function<List<RAW>, PROCESSED> merger) {
        this(folderName, codec, TardisRefined.GSON, merger);
    }

    /**
     * DO NOT USE THIS CONSTRUCTOR, use the factory method because this needs to use platform-specific logic.
     * <br> The default implementation does not send the sync packet, hence the need for a factory method.
     */
    protected MergeableCodecJsonReloadListener(String folderName, Codec<RAW> codec, Gson gson, final Function<List<RAW>, PROCESSED> merger) {
        this.folderName = folderName;
        this.codec = codec;
        this.merger = merger;
    }

    /**
     * Factory method to create an instance of the reload listener that will automatically redirect to the platform-specific implementations.
     * <br> DO NOT USE THE CONSTRUCTORS because the default implementation does not send the sync packet, hence the need for this factory method.
     *
     * @param folderName
     * @param codec
     * @param <RAW>       - The object that the codec is parsing json into
     * @param <PROCESSED> - The type of the object after merging the parsed objects. Can be the same type as RAW
     * @return
     */
    @ExpectPlatform
    public static <RAW, PROCESSED> MergeableCodecJsonReloadListener<RAW, PROCESSED> create(String folderName, Codec<PROCESSED> codec) {
        throw new AssertionError();
    }

    @Override
    protected Map<ResourceLocation, PROCESSED> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        //No need to define special syncing packet logic because the setSyncPacket method already subscribes us to the datapack sync events on the appropriate platform and defines the sync packet.
        LOGGER.info("Beginning loading of data for data loader: {}", this.folderName);
        Map<ResourceLocation, PROCESSED> map = new HashMap<>();

        Map<ResourceLocation, List<Resource>> resourceStacks = resourceManager.listResourceStacks(this.folderName, id -> id.getPath().endsWith(EXTENSION_NAME));

        map = this.mapValues(resourceStacks);

        LOGGER.info("Data loader for {} loaded {} jsons", this.folderName, this.data.size());
        return Map.copyOf(map);
    }

    /**
     * Main-thread processing, runs after prepare concludes
     **/
    @Override
    protected void apply(final Map<ResourceLocation, PROCESSED> processedData, final ResourceManager resourceManager, final ProfilerFiller profiler) {
        // now that we're on the main thread, we can finalize the data
        this.data = processedData;
    }

    /**
     * Define the logic for loading json entries, such as setting registry name
     *
     * @param inputs
     * @return
     */
    protected Map<ResourceLocation, PROCESSED> mapValues(Map<ResourceLocation, List<Resource>> inputs) {
        Map<ResourceLocation, PROCESSED> entries = new HashMap<>();

        for (var entry : inputs.entrySet()) {

            List<RAW> raws = new ArrayList<>();
            ResourceLocation fullId = entry.getKey();
            String fullPath = fullId.getPath(); // includes folderName/ and .json
            ResourceLocation key = new ResourceLocation(fullId.getNamespace(), fullPath.substring(this.folderName.length() + 1, fullPath.length() - EXTENSION_LENGTH));

            for (Resource resource : entry.getValue()) {
                try (Reader reader = resource.openAsReader()) {
                    JsonElement element = JsonParser.parseReader(reader);

                    // if we fail to parse json, log an error and continue
                    // if we succeeded, add the resulting T to the map
                    this.codec.decode(JsonOps.INSTANCE, element)
                            .get()
                            .ifLeft(result -> {
                                raws.add(result.getFirst());
                                LOGGER.info("Adding entry for {}", key);
                            })
                            .ifRight(partial -> LOGGER.error("Error deserializing json {} in folder {} from pack {}: {}", key, this.folderName, resource.sourcePackId(), partial.message()));
                } catch (Exception e) {
                    LOGGER.error(String.format(Locale.ENGLISH, "Error reading resource %s in folder %s from pack %s: ", key, this.folderName, resource.sourcePackId()), e);
                }
            }
            //Apply merging function on all raw files
            entries.put(key, this.merger.apply(raws));
        }
        return entries;
    }

    /**
     * Gets all entries loaded by the reload listener. This is the master registry for all entries.
     * <br> Since all Tardis Refined desktops are also in JSON form, this means we do not need to manually register entries to this map.
     *
     * @return
     */
    public Map<ResourceLocation, PROCESSED> getData() {
        return this.data;
    }

    /**
     * Overrides the existing data, ONLY use for the sync packet
     */
    public void setData(Map<ResourceLocation, PROCESSED> data) {
        this.data = data;
    }

    /**
     * Gets the name of the folder which we are reading JSON files from
     *
     * @return
     */
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * Defines the sync packet to use for syncing data from server to client.
     * <br> Also used for defining platform-specific logic to subscribe to a relevant datapack sync event depending on the platform.
     * <br> MUST be called in the main mod class constructor because we are subscribing to mod events.
     * <br> Doing so in any other location risks calling it too late to subscribe to events, meaning our sync packet never gets sent when needed.
     *
     * @param networkManager
     * @param packetFactory
     * @return
     */
    public MergeableCodecJsonReloadListener<RAW, PROCESSED> setSyncPacket(final NetworkManager networkManager, final Function<Map<ResourceLocation, PROCESSED>, MessageS2C> packetFactory) {
        return this;
    }

    /**
     * Common helper method to handle the packet syncing to send data to clients.
     *
     * @param player         - the player to send data to. If null (such as during server resource reload), will attempt to send to all players
     * @param networkManager
     * @param packetFactory  - applies the data to a sync packet that uses Message2C instance with a constructor containing a Map of entries
     */
    protected void handleSyncPacket(ServerPlayer player, final NetworkManager networkManager, final Function<Map<ResourceLocation, PROCESSED>, MessageS2C> packetFactory) {
        MessageS2C packet = packetFactory.apply(this.data);
        if (player == null)
            networkManager.sendToAllPlayers(packet);
        else networkManager.sendToPlayer(player, packet);
    }

}