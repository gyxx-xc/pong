package org.pongdev.pong.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.pongdev.pong.Pong;
import org.pongdev.pong.particle.ModParticles;

public class OpenChampagne {
    public static void open(ItemStack pStack, Entity entity, Level pLevel) {
        Pong.LOGGER.info("open");
        pStack.getOrCreateTag().putInt(ChampagneBottle.CAPABILITY_TAG, 1000);
        pStack.getOrCreateTag().putBoolean(ChampagneBottle.OPEN_TAG, true);
        double power = pStack.getOrCreateTag().getDouble(ChampagneBottle.POWER_TAG);
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

//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//        if (pContext.getLevel().isClientSide()) {
//            BlockPos positionClicked = pContext.getClickedPos();
//            Player player = pContext.getPlayer();
//
//            spawnFoundParticles(pContext, positionClicked);
//        }
//        return super.useOn(pContext);
//    }
//
//        private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked) {
//            for(int i = 0; i < 360; i++) {
//                if(i % 20 == 0) {
//                    pContext.getLevel().addParticle(ModParticles.SPLASH_PARTICLES.get(),
//                            positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
//                            Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
//                }
//            }
//        }
    private static void emmitParticle(Entity entity, double power) {
    }

    // TODO: play sound
    // TODO: the power will affect the type and the loudness of the sound
    private static void playSound(Entity entity, double power, Level level) {

    }
}
