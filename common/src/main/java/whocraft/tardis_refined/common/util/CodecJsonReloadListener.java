package whocraft.tardis_refined.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.NetworkManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Reusable codec based SimpleJsonResourceReloadListener
 * <br> Loads individual JSON files from a specified folder in a datapack, loads them into a registry then syncs the data from server to client via a sync packet.
 * <br> Cannot be used to merge multiple JSON files' data togethor, that would require a different implementation.
 *
 * @param <T>
 */
public class CodecJsonReloadListener<T> extends SimpleJsonResourceReloadListener {

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/CodecJsonReloadListener");


    protected final Codec<T> codec; // Make the codec protected access because some implementations may require extra logic to be added when we are decoding entries
    protected final String folderName;
    /**
     * The raw data that we parsed from json last time resources were reloaded
     **/
    protected Map<ResourceLocation, T> data = new HashMap<>();

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
    protected CodecJsonReloadListener(String folderName, Codec<T> codec) {
        this(folderName, codec, TardisRefined.GSON);
    }

    /**
     * DO NOT USE THIS CONSTRUCTOR, use the factory method because this needs to use platform-specific logic.
     * <br> The default implementation does not send the sync packet, hence the need for a factory method.
     */
    protected CodecJsonReloadListener(String folderName, Codec<T> codec, Gson gson) {
        super(gson, folderName);
        this.folderName = folderName;
        this.codec = codec;
    }

    /**
     * Factory method to create an instance of the reload listener that will automatically redirect to the platform-specific implementations.
     * <br> DO NOT USE THE CONSTRUCTORS because the default implementation does not send the sync packet, hence the need for this factory method.
     *
     * @param folderName
     * @param codec
     * @param <T>
     * @return
     */
    @ExpectPlatform
    public static <T> CodecJsonReloadListener<T> create(String folderName, Codec<T> codec) {
        throw new AssertionError();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        //No need to define special syncing packet logic because the setSyncPacket method already subscribes us to the datapack sync events on the appropriate platform and defines the sync packet.
        LOGGER.info("Beginning loading of data for data loader: {}", this.folderName);
        this.data = this.mapValues(jsons);
        LOGGER.info("Data loader for {} loaded {} entries", this.folderName, this.data.size());
    }

    /**
     * Define the logic for loading json entries, such as setting registry name
     *
     * @param inputs
     * @return
     */
    protected Map<ResourceLocation, T> mapValues(Map<ResourceLocation, JsonElement> inputs) {
        Map<ResourceLocation, T> entries = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : inputs.entrySet()) {
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();
            // if we fail to parse json, log an error and continue
            // if we succeeded, add the resulting T to the map
            this.codec.decode(JsonOps.INSTANCE, element)
                    .get()
                    .ifLeft(result -> {
                        entries.put(key, result.getFirst());
                        LOGGER.info("Adding entry {}", key);
                    })
                    .ifRight(partial -> LOGGER.error("Failed to parse data json for {} due to: {}", key, partial.message()));
        }
        return entries;
    }

    /**
     * Gets all entries loaded by the reload listener. This is the master registry for all entries.
     * <br> Since all Tardis Refined desktops are also in JSON form, this means we do not need to manually register entries to this map.
     *
     * @return
     */
    public Map<ResourceLocation, T> getData() {
        return data;
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
    public CodecJsonReloadListener<T> setSyncPacket(final NetworkManager networkManager, final Function<Map<ResourceLocation, T>, MessageS2C> packetFactory) {
        return this;
    }

    /**
     * Common helper method to handle the packet syncing to send data to clients.
     *
     * @param player         - the player to send data to. If null (such as during server resource reload), will attempt to send to all players
     * @param networkManager
     * @param packetFactory  - applies the data to a sync packet that uses Message2C instance with a constructor containing a Map of entries
     */
    protected void handleSyncPacket(ServerPlayer player, final NetworkManager networkManager, final Function<Map<ResourceLocation, T>, MessageS2C> packetFactory) {
        MessageS2C packet = packetFactory.apply(this.data);
        if (player == null)
            networkManager.sendToAllPlayers(packet);
        else networkManager.sendToPlayer(player, packet);
    }

}