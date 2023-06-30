package org.pongdev.pong.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.pongdev.pong.Pong;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.item.ChampagneSabre;
import org.pongdev.pong.item.Goblet;
import org.pongdev.pong.mobeffect.Drunk;

public class Registration {
    public static void register(IEventBus modBus){
        ITEMS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);
        MOB_EFFECT.register(modBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pong.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pong.MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Pong.MODID);


    public static final RegistryObject<Item> CHAMPAGNE = ITEMS.register(ChampagneBottle.ID, ChampagneBottle::new);
    public static final RegistryObject<Item> CHAMPAGNE_SABRE = ITEMS.register(ChampagneSabre.ID, ChampagneSabre::new);
    public static final RegistryObject<Item> GOBLET = ITEMS.register(Goblet.ID, Goblet::new);

    public static final RegistryObject<MobEffect> DRUNK = MOB_EFFECT.register("drunk", Drunk::new);

    public static final String MODTAB_ID ="pong_tab";
    public static final RegistryObject<CreativeModeTab> MODTAB = CREATIVE_MODE_TABS.register(MODTAB_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MODTAB_ID))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> CHAMPAGNE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(CHAMPAGNE.get());
                        output.accept(CHAMPAGNE_SABRE.get());
                        output.accept(GOBLET.get());
                    }).build() );
}
