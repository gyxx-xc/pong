package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.pongdev.pong.Pong;
import org.pongdev.pong.mobeffect.Drunk;

public class Goblet extends Item {
    public static final String ID = "goblet";
    private static final String CONTAIN_TAG = "contain";

    public Goblet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .alwaysEat()
                        .fast()
                        .build()
        ));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand thisHand) {
        ItemStack thisItem = pPlayer.getItemInHand(thisHand);
        if (!thisItem.getOrCreateTag().getString(CONTAIN_TAG).equals("")) { // not empty
            pPlayer.startUsingItem(thisHand);
            return InteractionResultHolder.consume(thisItem);
        }

        InteractionHand otherHand = thisHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherItem = pPlayer.getItemInHand(otherHand);
        if(otherItem.getItem() instanceof ChampagneBottle){
            int remainChampagne = otherItem.getOrCreateTag().getInt(ChampagneBottle.CAPABILITY_TAG);
            if (remainChampagne >= 100) {
                thisItem.getOrCreateTag().putBoolean("hand", thisHand == InteractionHand.MAIN_HAND);
                pPlayer.startUsingItem(thisHand);
                return InteractionResultHolder.consume(thisItem);
            } else {
                return InteractionResultHolder.fail(thisItem);
            }
        } // TODO: add the bucket can fill the goblet too
        return InteractionResultHolder.fail(thisItem);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 15;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        if (!pStack.getOrCreateTag().getString(CONTAIN_TAG).equals("")) // not empty
            return UseAnim.DRINK;
        else
            return UseAnim.NONE; //TODO: get this right
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pStack.getOrCreateTag().getString(CONTAIN_TAG).equals("")) {
            pStack.getOrCreateTag().putString(CONTAIN_TAG, "");
            // we may change this in the future
            // but for now, the containing can only be the champagne
            int level = pLivingEntity.getPersistentData().getInt(Drunk.DRUNK_LEVEL);
            if (level <= 3) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 2));
            } else if (level <= 5) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 4));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 500, 1));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 500, 2));
            } else if (level <= 10) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 4));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 500, 1));
            } else if (level <= 20) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 500, 1));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, 2));
            } else {
                pLivingEntity.kill();
            }
            pLivingEntity.getPersistentData().putInt(Drunk.DRUNK_LEVEL, level+1);
        } else {
            pStack.getOrCreateTag().putString(CONTAIN_TAG, "champagne");
            ItemStack otherItem = pLivingEntity.getItemInHand(
                    pStack.getOrCreateTag().getBoolean("hand") ?
                            InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            int remainChampagne = otherItem.getOrCreateTag().getInt(ChampagneBottle.CAPABILITY_TAG);
            otherItem.getOrCreateTag().putInt(ChampagneBottle.CAPABILITY_TAG, remainChampagne-100);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
