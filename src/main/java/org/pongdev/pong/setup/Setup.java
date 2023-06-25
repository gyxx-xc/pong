package org.pongdev.pong.setup;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.pongdev.pong.Pong;

@Mod.EventBusSubscriber(modid = Pong.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Setup {
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        Pong.LOGGER.info("HELLO FROM PONG!");
        Pong.LOGGER.info("OVER DRINK IS HARMFUL TO YOUR HEALTH"); // 过度饮酒,有害健康 ()
    }
}
