package whocraft.tardis_refined.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlUtil;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Triple;
import whocraft.tardis_refined.TRConfig;

import java.util.Objects;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.NEW_ENTITY;
import static net.minecraft.client.renderer.RenderStateShard.*;

public class TRShaders {
    public static ShaderInstance GLOW_SHADER;
    public static ShaderInstance SNOW_SHADER;


    @Environment(EnvType.CLIENT)
    public static boolean shouldUseCompatMode() {
        return !GlUtil.getVendor().toLowerCase().contains("nvidia");
    }


    public static RenderType translucentWithSnow(ResourceLocation texture, boolean show) {
        ResourceLocation snowTexture = new ResourceLocation("minecraft", "textures/block/snow.png");


        // Has to be separate, using java OR operator would allow the snow to render even if the user has internal shaders turned off
        if(!TRConfig.CLIENT.USE_INTERNAL_SHADERS.get()){
            return RenderType.entityTranslucent(texture);
        }

        if (!show) return RenderType.entityTranslucent(texture);

        // Create an ImmutableList of Triple for the textures
        ImmutableList<Triple<ResourceLocation, Boolean, Boolean>> textureList = ImmutableList.of(
                Triple.of(texture, false, false),
                Triple.of(snowTexture, false, false)
        );

        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> TRShaders.SNOW_SHADER))
                .setTextureState(new RenderStateShard.MultiTextureStateShard(textureList))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setOverlayState(OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .setLightmapState(LIGHTMAP)
                .createCompositeState(false);

        return RenderType.create(
                "nivis",
                NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                false,
                state
        );
    }



    public static RenderType glow(ResourceLocation texture, float intensity) {

        if(true){ // For now, we will stick to Mojangs implementation until we get it right
            return RenderType.entityTranslucentEmissive(texture);
        }

        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> {
                    ShaderInstance glowShader = TRShaders.GLOW_SHADER;
                    Objects.requireNonNull(glowShader.getUniform("GlowIntensity")).set(intensity);
                    return glowShader;
                }))
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY)
                .setWriteMaskState(COLOR_WRITE)
                .createCompositeState(true);

        return RenderType.create(
                "glowing_texture",
                NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                false,
                state
        );
    }



}
