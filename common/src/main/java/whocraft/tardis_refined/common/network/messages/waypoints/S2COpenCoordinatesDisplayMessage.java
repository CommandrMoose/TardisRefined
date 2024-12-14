package whocraft.tardis_refined.common.network.messages.waypoints;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;

import java.util.ArrayList;
import java.util.List;

public record S2COpenCoordinatesDisplayMessage(List<ResourceKey<Level>> levels, CoordInputType coordInputType, TardisNavLocation tardisNavLocation) implements CustomPacketPayload, NetworkManager.Handler<S2COpenCoordinatesDisplayMessage> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<S2COpenCoordinatesDisplayMessage> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_coords_display"));

    // Serializer for this message
    public static final StreamCodec<FriendlyByteBuf, S2COpenCoordinatesDisplayMessage> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeNbt(ref.tardisNavLocation.serialise());
                buf.writeUtf(ref.coordInputType.name());
                buf.writeInt(ref.levels.size());
                for (ResourceKey<Level> level : ref.levels) {
                    buf.writeResourceKey(level);
                }
            },
            buf -> {
                TardisNavLocation navLocation = TardisNavLocation.deserialize(buf.readNbt());
                CoordInputType inputType = CoordInputType.valueOf(buf.readUtf());
                List<ResourceKey<Level>> levels = new ArrayList<>();
                int size = buf.readInt();
                for (int i = 0; i < size; i++) {
                    levels.add(buf.readResourceKey(Registries.DIMENSION));
                }
                return new S2COpenCoordinatesDisplayMessage(levels, inputType, navLocation);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2COpenCoordinatesDisplayMessage value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleDisplay();
        }
    }

    @Environment(EnvType.CLIENT)
    private void handleDisplay() {
        // Open the coordinates screen on the client
        ScreenHandler.openCoordinatesScreen(levels, coordInputType, tardisNavLocation);
    }
}
