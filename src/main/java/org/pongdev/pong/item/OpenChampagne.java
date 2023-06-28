package org.pongdev.pong.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.pongdev.pong.Pong;

public class OpenChampagne {
    public static void open(ItemStack pStack, Entity entity, Level pLevel) {
        Pong.LOGGER.info("open");
        double power = pStack.getOrCreateTag().getDouble("power");
        playSound(entity, power, pLevel);
        emmitParticle(entity, power);
        shootPlug(entity, power);
    }

    // TODO: refer to how the bow do, create a plug entity
    // TODO: the power will decided the speed and the damage of a plug
    private static void shootPlug(Entity entity, double power) {

    }

    // TODO: spawn particle act like some water is splash
    // TODO: the particle should be white cause the bubble
    // TODO: the power will affect the splash range of the particle
    private static void emmitParticle(Entity entity, double power) {

    }

    // TODO: play sound
    // TODO: the power will affect the type and the loudness of the sound
    private static void playSound(Entity entity, double power, Level level) {

    }
}
