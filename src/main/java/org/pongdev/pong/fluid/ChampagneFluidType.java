package org.pongdev.pong.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import org.pongdev.pong.setup.Registration;

public class ChampagneFluidType {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");

    public static final RegistryObject<FluidType> CHAMPAGNE_FLUID_TYPE = register("champagne_fluid",
            FluidType.Properties.create()
                    .lightLevel(2)
                    .density(5)
                    .viscosity(1)
                    .canHydrate(true)
                    .sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return Registration.FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, null,
                0xF7F7D1A0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }
}
