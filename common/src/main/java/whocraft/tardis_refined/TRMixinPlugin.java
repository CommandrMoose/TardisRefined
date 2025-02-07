package whocraft.tardis_refined;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class TRMixinPlugin implements IMixinConfigPlugin {

    public static final boolean HAS_SODIUM;

    static {
        HAS_SODIUM = hasClass("net.caffeinemc.mods.sodium.client.render.immediate.model.EntityRenderer")
                || hasClass("me.jellysquid.mods.sodium.client.render.immediate.model.EntityRenderer");
    }

    private static boolean hasClass(String name) {
        try {
            // This does *not* load the class!
            MixinService.getService().getBytecodeProvider().getClassNode(name);
            return true;
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
    }

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
            TardisRefined.LOGGER.info("Checking for Sodium, found: {}", HAS_SODIUM);

            if(HAS_SODIUM){
                TardisRefined.LOGGER.info("Sodium Detected, enabling {}", mixinClassName);
            }

            return HAS_SODIUM;
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
