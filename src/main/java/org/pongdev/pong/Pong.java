package org.pongdev.pong;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.pongdev.pong.setup.Registration;
import org.pongdev.pong.setup.datagen.DataGeneration;
import org.slf4j.Logger;

@Mod(Pong.MODID)
public class Pong {
    public static final String MODID = "pong";
    public static final Logger LOGGER = LogUtils.getLogger();
    public Pong(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.register(modEventBus);
        modEventBus.addListener(DataGeneration::onGatherData);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
