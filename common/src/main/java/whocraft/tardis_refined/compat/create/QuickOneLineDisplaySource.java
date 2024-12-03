package whocraft.tardis_refined.compat.create;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.constants.ModMessages;

public class QuickOneLineDisplaySource extends SingleLineDisplaySource {

    private final TardisInfo source;

    public QuickOneLineDisplaySource(TardisInfo source) {
        this.source = source;
    }

    @Override
    protected MutableComponent provideLine(DisplayLinkContext context, DisplayTargetStats stats) {

        if (context.level() instanceof ServerLevel serverLevel) {
            boolean isPresent = TardisLevelOperator.get(serverLevel).isPresent();
            if (isPresent) {
                return source.provideInfo(TardisLevelOperator.get(serverLevel).get());
            }
        }

        return Component.translatable(ModMessages.HARDWARE_OFFLINE);
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext context) {
        return false;
    }

    @Override
    public int getPassiveRefreshTicks() {
        return 10;
    }

    public interface TardisInfo {
        MutableComponent provideInfo(TardisLevelOperator tardisLevelOperator);

        ResourceLocation getId();
    }
}
