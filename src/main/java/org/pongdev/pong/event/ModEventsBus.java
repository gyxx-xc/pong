package org.pongdev.pong.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.pongdev.pong.Pong;
import org.pongdev.pong.particle.ModParticles;
import org.pongdev.pong.particle.custom.SplashParticles;

@Mod.EventBusSubscriber(modid = Pong.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventsBus {

    @SubscribeEvent
    public static void registerParticleProvider(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.SPLASH_PARTICLES.get(),
                SplashParticles.Provider::new);
    }

}
