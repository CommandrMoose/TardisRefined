package whocraft.tardis_refined;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import whocraft.tardis_refined.common.util.Platform;

import java.util.List;
import java.util.Set;

public class TRMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

        if (mixinClassName.contains("whocraft.tardis_refined.mixin.render.SodiumFixMixin")) {
            boolean isSodiumInstalled = Platform.isModLoaded("sodium");
            TardisRefined.LOGGER.info("Checking for Sodium, found: {}", isSodiumInstalled);

            if(isSodiumInstalled){
                TardisRefined.LOGGER.info("Sodium Detected, enabling {}", mixinClassName);
            }

            return isSodiumInstalled;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
