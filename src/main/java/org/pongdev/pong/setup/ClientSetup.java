package org.pongdev.pong.setup;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.pongdev.pong.Pong;
import org.pongdev.pong.block.RackRender;

@Mod.EventBusSubscriber(modid = Pong.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    Registration.CHAMPAGNE.get(),
                    new ResourceLocation(Pong.MODID, "power"),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getOrCreateTag().getFloat("power"));
            ItemProperties.register(
                    Registration.CHAMPAGNE.get(),
                    new ResourceLocation(Pong.MODID, "open"),
                    (itemStack, clientWorld, livingEntity, id) ->
                            itemStack.getOrCreateTag().getBoolean("open") ? 1 : 0);
        });
    }

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registration.CHAMPAGNE_RACK_ENTITY.get(), RackRender::new);
    }
}
