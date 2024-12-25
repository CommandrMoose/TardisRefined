package whocraft.tardis_refined.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class ParticleGallifrey extends BaseAshSmokeParticle {


    private final SpriteSet sprites;

    protected ParticleGallifrey(ClientLevel arg, double d, double e, double f, double g, double h, double i, float j, SpriteSet arg2) {
        super(arg, d, e, f, 0.0F, 0.1F, 0.0F, g, h, i, j, arg2, 0.3F, 8, -0.1F, true);
        this.sprites = arg2;
        setColor(1, 1, 1);
        setLifetime(lifetime * 2);
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getQuadSize(float f) {
        return 0.05F;
    }

    @Override
    public void tick() {
        super.tick();
        setColor(1, 1, 1);
        if (age % 40 == 0) {
            this.setSpriteFromAge(this.sprites);
        }
    }


    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet arg) {
            this.sprite = arg;
        }


        public Particle createParticle(SimpleParticleType arg, ClientLevel arg2, double d, double e, double f, double g, double h, double i) {
            ParticleGallifrey particleGallifrey = new ParticleGallifrey(arg2, d, e, f, g, h, i, 1.0F, this.sprite);
            particleGallifrey.pickSprite(this.sprite);
            return particleGallifrey;
        }
    }

    public static class ARSVinesParticle implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public ARSVinesParticle(SpriteSet arg) {
            this.sprite = arg;
        }

        @Override
        public Particle createParticle(SimpleParticleType arg, ClientLevel arg2, double d, double e, double f, double g, double h, double i) {
            SuspendedParticle suspendedParticle = new SuspendedParticle(arg2, this.sprite, d, e, f, 0.0, -0.8f, 0.0);
            suspendedParticle.setLifetime(Mth.randomBetweenInclusive(arg2.random, 500, 1000));
            suspendedParticle.gravity = 0.01f;
            suspendedParticle.setColor(0.4F, 0.4F, 0.7F);
            return suspendedParticle;
        }

    }
}
