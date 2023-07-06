package org.pongdev.pong.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import org.pongdev.pong.Pong;
import org.pongdev.pong.setup.Registration;

public class ChampagneSabre extends SwordItem {
    public static final String ID = "champagne_sabre";

    public ChampagneSabre() {
        super(Tiers.IRON, -2, 6, new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        InteractionHand otherHand = pUsedHand == InteractionHand.MAIN_HAND ?
                InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherStack = pPlayer.getItemInHand(otherHand);
        ItemStack thisStack = pPlayer.getItemInHand(pUsedHand);
        if (otherStack.getItem() instanceof ChampagneBottle) {
            if (!otherStack.getOrCreateTag().getBoolean(ChampagneBottle.OPEN_TAG)) {
                pPlayer.startUsingItem(pUsedHand);
                return InteractionResultHolder.success(thisStack);
            }
        }
        return InteractionResultHolder.fail(thisStack);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 2;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player) {
            ItemStack mainItem = pLivingEntity.getItemInHand(InteractionHand.MAIN_HAND);
            ItemStack offItem = pLivingEntity.getItemInHand(InteractionHand.OFF_HAND);
            ItemStack champagneStack = mainItem.getItem() instanceof ChampagneBottle ? mainItem : offItem;

            if (champagneStack.getCount() == 1){
                OpenChampagne.open(champagneStack, pLivingEntity, pLevel);
            } else {
                champagneStack.shrink(1);
                ItemStack newItemStack = new ItemStack(Registration.CHAMPAGNE.get());
                OpenChampagne.open(newItemStack, pLivingEntity, pLevel);
                Player player = (Player) pLivingEntity;
                if (!player.getInventory().add(newItemStack))
                    player.drop(newItemStack, false);
            }
        }
        pStack.setDamageValue(pStack.getDamageValue() + 1);
        return pStack;
    }
}
