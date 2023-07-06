package org.pongdev.pong.setup;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.pongdev.pong.Pong;
import org.pongdev.pong.block.ChampagneBottleBlock;
import org.pongdev.pong.block.RackRender;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.item.Goblet;

@Mod.EventBusSubscriber(modid = Pong.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    Registration.CHAMPAGNE.get(),
                    new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getOrCreateTag().getFloat(ChampagneBottle.POWER_TAG));
            ItemProperties.register(
                    Registration.CHAMPAGNE.get(),
                    new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getOrCreateTag().getBoolean(ChampagneBottle.POWER_TAG) ? 1 : 0);
            ItemProperties.register(
                    Registration.CHAMPAGNE_SABRE.get(),
                    new ResourceLocation(Pong.MODID, "damage"),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getUseDuration());
            ItemProperties.register(
                    Registration.CHAMPAGNE_SABRE.get(),
                    new ResourceLocation(Pong.MODID, Goblet.CONTAIN_TAG),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getOrCreateTag().getString(Goblet.CONTAIN_TAG).equals("champagne") ? 1 : 0);
        });
    }

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registration.CHAMPAGNE_RACK_ENTITY.get(), RackRender::new);
    }
}
