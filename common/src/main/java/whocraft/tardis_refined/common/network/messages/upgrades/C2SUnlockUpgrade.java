package whocraft.tardis_refined.common.network.messages.upgrades;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.util.PlayerUtil;

import java.util.Objects;

public record C2SUnlockUpgrade(Upgrade upgrade) implements CustomPacketPayload, NetworkManager.Handler<C2SUnlockUpgrade> {

    public static final CustomPacketPayload.Type<C2SUnlockUpgrade> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "unlock_upgrade"));

    public static final StreamCodec<FriendlyByteBuf, C2SUnlockUpgrade> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeResourceLocation(Objects.requireNonNull(TRUpgrades.UPGRADE_REGISTRY.getKey(ref.upgrade()))),
            buf -> new C2SUnlockUpgrade(TRUpgrades.UPGRADE_REGISTRY.get(buf.readResourceLocation()))
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SUnlockUpgrade value, NetworkManager.Context context) {
        ServerLevel serverLevel = (ServerLevel) context.getPlayer().level();
        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            UpgradeHandler upgradeHandler = tardisLevelOperator.getUpgradeHandler();
            boolean available = !upgradeHandler.isUpgradeUnlocked(value.upgrade()) &&
                    upgradeHandler.getUpgradePoints() >= value.upgrade().getSkillPointsRequired();

            if (available) {
                upgradeHandler.setUpgradePoints(upgradeHandler.getUpgradePoints() - value.upgrade().getSkillPointsRequired());
                upgradeHandler.unlockUpgrade(value.upgrade());

                CompoundTag nbt = upgradeHandler.saveData(new CompoundTag());
                new S2CDisplayUpgradeScreen(nbt).send(context.getPlayer());
            } else {
                PlayerUtil.sendMessage(context.getPlayer(), "Not enough points or upgrade already unlocked.", true);
            }
        });
    }
}
