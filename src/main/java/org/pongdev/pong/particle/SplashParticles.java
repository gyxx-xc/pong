package org.pongdev.pong.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.security.PublicKey;

// TODO: change the name of the class
// SplashParticles is not a util class of SplashParticle
public class SplashParticles extends TextureSheetParticle {

    public static final String ID = "splash_particles";

    public SplashParticles(ClientLevel pLevel, double pX, double pY, double pZ,
                              SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.999999999999999999999F;
        this.lifetime = 50;
        this.gravity = 0.4F;
        this.setSpriteFromAge(spriteSet);
        this.speedUpWhenYMotionIsBlocked = true;
    }

    @Override
    public void tick() {
        super.tick();
        fadeout();
    }

    private void fadeout(){
        this.alpha = age > 30 ? (1 - (float)(age-30)/(lifetime-30)) : 1;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new SplashParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
