package org.pongdev.pong.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.pongdev.pong.Pong;
import org.pongdev.pong.mobeffect.Drunk;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Pong.MODID)
public class Setup {
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        Pong.LOGGER.info("HELLO FROM PONG!");
        Pong.LOGGER.info("TAKE CARE: OVER DRINK IS HARMFUL TO YOUR HEALTH"); // 过度饮酒,有害健康 (?)
    }

    @SubscribeEvent
    public static void playerWakeUpSetup(PlayerWakeUpEvent event) {
        event.getEntity().getPersistentData().putInt(Drunk.DRUNK_LEVEL, 0);
    }
}
