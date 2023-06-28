package org.pongdev.pong.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class ChampagneSabre extends SwordItem {
    public static final String ID = "champagne_sabre";

    public ChampagneSabre() {
        super(Tiers.IRON, -2, 6, new Item.Properties());
    }

    // TODO: open one champagne once
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        InteractionHand otherHand = pUsedHand == InteractionHand.MAIN_HAND ?
                InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherStack = pPlayer.getItemInHand(otherHand);
        ItemStack thisStack = pPlayer.getItemInHand(pUsedHand);
        if (otherStack.getItem() instanceof Champagne){
            if(!otherStack.getOrCreateTag().getBoolean(Champagne.OPEN_TAG)) {
                otherStack.getOrCreateTag().putBoolean(Champagne.OPEN_TAG, true);
                OpenChampagne.open(otherStack, pPlayer, pLevel);
                pPlayer.startUsingItem(pUsedHand);
                return InteractionResultHolder.success(thisStack);
            }
        }
        return InteractionResultHolder.fail(thisStack);
    }
}
