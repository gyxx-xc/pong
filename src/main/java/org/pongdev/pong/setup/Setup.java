package org.pongdev.pong.setup;

import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.pongdev.pong.Pong;
import org.pongdev.pong.mobeffect.Drunk;

@Mod.EventBusSubscriber(modid = Pong.MODID)
public class Setup {
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        Pong.LOGGER.info("HELLO FROM PONG!");
        Pong.LOGGER.info("TAKE CARE: OVER DRINK IS HARMFUL TO YOUR HEALTH"); // 过度饮酒,有害健康 (?)
    }

    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        event.getEntity().getPersistentData().putInt(Drunk.DRUNK_LEVEL, 0);
    }
}
