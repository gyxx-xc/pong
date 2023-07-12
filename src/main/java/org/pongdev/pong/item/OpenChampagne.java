package org.pongdev.pong.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.pongdev.pong.Pong;
import org.pongdev.pong.setup.Registration;

public class OpenChampagne {
    public static void open(ItemStack pStack, Entity entity, Level pLevel) {
        Pong.LOGGER.info("open");
        pStack.getOrCreateTag().putInt(ChampagneBottle.CAPABILITY_TAG, 1000);
        pStack.getOrCreateTag().putBoolean(ChampagneBottle.OPEN_TAG, true);
        double power = pStack.getOrCreateTag().getDouble(ChampagneBottle.POWER_TAG);
        playSound(entity, power, pLevel);
        emmitParticle(entity, power, pLevel);
        shootPlug(entity, power);
    }

    // TODO: refer to how the bow do, create a plug entity
    // TODO: the power will decided the speed and the damage of a plug
    private static void shootPlug(Entity entity, double power) {

    }

    // TODO: the power will affect the splash range of the particle
    private static void emmitParticle(Entity entity, double power, Level level) {
        if (level.isClientSide()){
            Pong.LOGGER.info("asd");
            Vec3 entityPos = entity.getPosition(0);
            level.addParticle(Registration.SPLASH_PARTICLES.get(),
                    entityPos.x + 1d, entityPos.y, entityPos.z + 1d,
                    0.15d, 0.15d, 0.15d);
        }
    }

    // TODO: play sound
    // TODO: the power will affect the type and the loudness of the sound
    private static void playSound(Entity entity, double power, Level level) {

    }
}
