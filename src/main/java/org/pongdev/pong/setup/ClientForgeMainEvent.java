package org.pongdev.pong.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.pongdev.pong.Pong;

@Mod.EventBusSubscriber(modid = Pong.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeMainEvent {
    @SubscribeEvent
    public static void onEntityViewRender(final ViewportEvent event) {
        try {
            Pong.LOGGER.info(event.getCamera().getEntity().xRotO+" 0");
            Pong.LOGGER.info(event.getCamera().getEntity().getXRot()+" 1");
        } catch (Exception ignored) {

        }
    }
}
