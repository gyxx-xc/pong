package org.pongdev.pong.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.pongdev.pong.Pong;
import org.pongdev.pong.fluid.ChampagneFluidType;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.item.ChampagneSabre;
import org.pongdev.pong.item.Goblet;
import org.pongdev.pong.mobeffect.Drunk;

public class Registration {
    public static void register(IEventBus modBus){
        ITEMS.register(modBus);
        BLOCKS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);
        MOB_EFFECTS.register(modBus);
        FLUIDS.register(modBus);
        FLUID_TYPES.register(modBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pong.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Pong.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pong.MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Pong.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Pong.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Pong.MODID);


    public static final RegistryObject<Item> CHAMPAGNE = ITEMS.register(ChampagneBottle.ID, ChampagneBottle::new);
    public static final RegistryObject<Item> CHAMPAGNE_SABRE = ITEMS.register(ChampagneSabre.ID, ChampagneSabre::new);
    public static final RegistryObject<Item> GOBLET = ITEMS.register(Goblet.ID, Goblet::new);

    public static final RegistryObject<MobEffect> DRUNK = MOB_EFFECTS.register("drunk", Drunk::new);

    public static final RegistryObject<FlowingFluid> SOURCE_CHAMPAGNE = FLUIDS.register("champagne_fluid",
            () -> new ForgeFlowingFluid.Source(Registration.CHAMPAGNE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CHAMPAGNE = FLUIDS.register("champagne_water",
            () -> new ForgeFlowingFluid.Flowing(Registration.CHAMPAGNE_FLUID_PROPERTIES));
    public static final RegistryObject<LiquidBlock> CHAMPAGNE_FLUID_BLOCK = BLOCKS.register("champagne_fluid_block",
            () -> new LiquidBlock(SOURCE_CHAMPAGNE, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final ForgeFlowingFluid.Properties CHAMPAGNE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ChampagneFluidType.CHAMPAGNE_FLUID_TYPE, SOURCE_CHAMPAGNE, FLOWING_CHAMPAGNE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CHAMPAGNE_FLUID_BLOCK);


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
