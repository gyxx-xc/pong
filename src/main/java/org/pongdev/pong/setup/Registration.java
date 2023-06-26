package org.pongdev.pong.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.pongdev.pong.Pong;
import org.pongdev.pong.item.Champagne;

public class Registration {
    public static void register(IEventBus modBus){
        ITEMS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pong.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pong.MODID);

    public static final RegistryObject<Item> CHAMPAGNE = ITEMS.register(Champagne.ID, Champagne::new);

    public static final String MODTAB_ID ="pong_tab";
    public static final RegistryObject<CreativeModeTab> MODTAB = CREATIVE_MODE_TABS.register(MODTAB_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MODTAB_ID))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> CHAMPAGNE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(CHAMPAGNE.get());
                    }).build() );
}
