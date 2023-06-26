package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.pongdev.pong.Pong;

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
        return 40;
    }
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remain) {
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null) return;
        if(remain == 1){
            compoundTag.putBoolean("a", false);
        }
        if(livingEntity instanceof Player){
            Vec3 view = livingEntity.getViewVector(1.0f);
            Vec3 view0 = new Vec3(compoundTag.getDouble("lastX"),
                    compoundTag.getDouble("lastY"),
                    compoundTag.getDouble("lastZ"));
            double d = Math.acos(view.dot(view0) > 1 ? 1 : view.dot(view0));
            compoundTag.putDouble("lastX", view.x);
            compoundTag.putDouble("lastY", view.y);
            compoundTag.putDouble("lastZ", view.z);
        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.EAT;
    }
}
