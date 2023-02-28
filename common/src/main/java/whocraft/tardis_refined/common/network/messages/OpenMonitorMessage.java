package whocraft.tardis_refined.common.network.messages;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.CancelDesktopScreen;
import whocraft.tardis_refined.client.screen.MonitorScreen;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.constants.NbtConstants;


public class OpenMonitorMessage extends MessageS2C {

    private final boolean desktopGenerating;
    private final BlockPos currentPos;
    private final Direction currentDir;
    private final BlockPos targetPos;
    private final Direction targetDir;
    private final ResourceKey<Level> currentKey;
    private final ResourceKey<Level> targetKey;

    public OpenMonitorMessage(boolean desktopGenerating, TardisNavLocation currentLocation, TardisNavLocation targetLocation) {
        this.desktopGenerating = desktopGenerating;
        this.currentPos = currentLocation.position;
        this.currentDir = currentLocation.rotation;
        this.targetPos = targetLocation.position;
        this.targetDir = targetLocation.rotation;
        this.currentKey = currentLocation.getLevel().dimension();
        this.targetKey = targetLocation.getLevel().dimension();
    }

    public OpenMonitorMessage(FriendlyByteBuf friendlyByteBuf) {
        this.desktopGenerating = friendlyByteBuf.readBoolean();
        this.currentPos = friendlyByteBuf.readBlockPos();
        this.currentDir = Direction.from2DDataValue(friendlyByteBuf.readInt());
        this.targetPos = friendlyByteBuf.readBlockPos();
        this.targetDir = Direction.from2DDataValue(friendlyByteBuf.readInt());
        this.currentKey = friendlyByteBuf.readResourceKey(Registries.DIMENSION);
        this.targetKey = friendlyByteBuf.readResourceKey(Registries.DIMENSION);

    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.OPEN_MONITOR;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.desktopGenerating);
        buf.writeBlockPos(this.currentPos);
        buf.writeInt(this.currentDir.get2DDataValue());
        buf.writeBlockPos(this.targetPos);
        buf.writeInt(this.targetDir.get2DDataValue());
        buf.writeResourceKey(this.currentKey);
        buf.writeResourceKey(this.targetKey);
    }


    @Override
    public void handle(MessageContext context) {
        handleScreens();
    }

    @Environment(EnvType.CLIENT)
    private void handleScreens() {
        // Open the monitor.
        if (this.desktopGenerating) {
            Minecraft.getInstance().setScreen(new CancelDesktopScreen());
        } else {

            var currentLoc = new TardisNavLocation(this.currentPos, this.currentDir, this.currentKey);
            var targetLoc = new TardisNavLocation(this.targetPos, this.targetDir, this.targetKey);

            Minecraft.getInstance().setScreen(new MonitorScreen(currentLoc, targetLoc));
        }
    }
}
