package whocraft.tardis_refined.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Objects;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.NEW_ENTITY;
import static net.minecraft.client.renderer.RenderStateShard.*;

public class TRShaders {
    public static ShaderInstance GLOW_SHADER;
    public static ShaderInstance SNOW_SHADER;

    public static RenderType translucentWithSnow(ResourceLocation texture, boolean show) {
        ResourceLocation snowTexture = new ResourceLocation("minecraft", "textures/block/snow.png");

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
