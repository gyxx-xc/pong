package org.pongdev.pong.item;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import org.pongdev.pong.setup.Registration;

public class Goblet extends Item {
    public static final String ID = "goblet";
    public static final String CONTAIN_TAG = "contain";

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
            if (!pLevel.isClientSide) {
                int level = 0;
                MobEffectInstance drunk = pLivingEntity.getEffect(Registration.DRUNK.get());
                if (drunk != null)
                    level = drunk.getAmplifier();
                pLivingEntity.addEffect(new MobEffectInstance(Registration.DRUNK.get(), 3000, level+1));
                if (level <= 3) {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, level));
                } else if (level <= 5) {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 4));
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 500, 1));
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 500, (level - 3)));
                } else if (level <= 10) {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 4));
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 500, level - 5));
                } else if (level <= 20) {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (level-10)*100+100, 100));
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, level - 10));
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 500, 10));
                } else {
                    pLivingEntity.kill();
                }
            }
        } else {
            ItemStack newItemStack = new ItemStack(pStack.getItem(), pStack.getCount()-1, pStack.getTag());
            pStack.getOrCreateTag().putString(CONTAIN_TAG, "champagne");

            if (pStack.getCount() >= 1) {
                Player player = (Player) pLivingEntity;
                if (!player.getInventory().add(newItemStack))
                    player.drop(newItemStack, false);
            }
            pStack.setCount(1);

            ItemStack otherItem = pLivingEntity.getItemInHand(
                    pStack.getOrCreateTag().getBoolean("hand") ?
                            InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            int remainChampagne = otherItem.getOrCreateTag().getInt(ChampagneBottle.CAPABILITY_TAG);
            otherItem.getOrCreateTag().putInt(ChampagneBottle.CAPABILITY_TAG, remainChampagne-100);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
