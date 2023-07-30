package org.pongdev.pong.setup;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import org.pongdev.pong.block.ChampagneBottleBlock;
import org.pongdev.pong.block.ChampagneRack;
import org.pongdev.pong.block.RackEntity;
import org.pongdev.pong.entity.PlugEntity;
import org.pongdev.pong.fluid.ChampagneFluidType;
import org.pongdev.pong.item.*;
import org.pongdev.pong.mobeffect.Drunk;
import org.pongdev.pong.particle.SplashParticles;

public class Registration {
    public static void register(IEventBus modBus){
        ITEMS.register(modBus);
        BLOCKS.register(modBus);
        BLOCK_ENTITIES.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);
        MOB_EFFECTS.register(modBus);
        FLUIDS.register(modBus);
        FLUID_TYPES.register(modBus);
        PARTICLE_TYPES.register(modBus);
        ENTITY_TYPES.register(modBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pong.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Pong.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Pong.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pong.MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Pong.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Pong.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Pong.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Pong.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Pong.MODID);


    public static final RegistryObject<Item> CHAMPAGNE = ITEMS.register(ChampagneBottle.ID, ChampagneBottle::new);
    public static final RegistryObject<Item> CHAMPAGNE_SABRE = ITEMS.register(ChampagneSabre.ID, ChampagneSabre::new);
    public static final RegistryObject<Item> GOBLET = ITEMS.register(Goblet.ID, Goblet::new);
    public static final RegistryObject<Item> PLUG = ITEMS.register(PlugItem.ID, PlugItem::new);
    public static final RegistryObject<Item> DEBUG_ROD = ITEMS.register(DebugRod.ID, DebugRod::new);

    public static final RegistryObject<EntityType<PlugEntity>> PLUG_ENTITY = ENTITY_TYPES.register(PlugItem.ID,
            () -> EntityType.Builder.of(PlugEntity::new, MobCategory.MISC)
                    .sized(0.125f, 0.0625f)
                    .build(PlugItem.ID));

    public static final RegistryObject<Block> CHAMPAGNE_BOTTLE_BLOCK = BLOCKS.register(ChampagneBottle.ID, ChampagneBottleBlock::new);
    public static final RegistryObject<Block> CHAMPAGNE_RACK_BLOCK = BLOCKS.register(ChampagneRack.ID, ChampagneRack::new);
    public static final RegistryObject<Item> CHAMPAGNE_RACK_ITEM = ITEMS.register(ChampagneRack.ID, () -> new BlockItem(CHAMPAGNE_RACK_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockEntityType<RackEntity>> CHAMPAGNE_RACK_ENTITY = BLOCK_ENTITIES.register(
            ChampagneRack.ID,
            () -> BlockEntityType.Builder.of(RackEntity::new, CHAMPAGNE_RACK_BLOCK.get()).build(null)
    );

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

    public static final RegistryObject<SimpleParticleType> SPLASH_PARTICLES =
            PARTICLE_TYPES.register(SplashParticles.ID, () -> new SimpleParticleType(true));

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
                        output.accept(CHAMPAGNE_RACK_ITEM.get());
                        output.accept(PLUG.get());
                    }).build() );
}
