package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Champagne extends Item {
    public static final String ID = "champagne";
    public Champagne() {
        super(new Item.Properties());
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null) {
            compoundTag = itemStack.getOrCreateTag();
            compoundTag.putBoolean("a", true);
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        } else if(compoundTag.getBoolean("a")){
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 10;
    }

/*    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack itemStack = context.getItemInHand();
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null){
            compoundTag = itemStack.getOrCreateTag();
            compoundTag.putBoolean("a", true);
            return InteractionResult.SUCCESS;
        } else {
            if (compoundTag.getBoolean("a")) {
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }*/

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remain) {
        CompoundTag compoundTag = itemStack.getTag();
        //Pong.LOGGER.info(Boolean.toString(compoundTag.getBoolean("a")));
        if(remain == 1){
            if(compoundTag != null)
                compoundTag.putBoolean("a", false);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.EAT;
    }
}
