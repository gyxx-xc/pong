package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault // no warning then...
public class Champagne extends Item {
    public static final String ID = "champagne";
    public Champagne() {
        super(new Item.Properties().durability(1000));
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
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
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remain) {
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null) return;
        if(remain == 1){
            compoundTag.putBoolean("a", false);
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pIsSelected){
            CompoundTag compoundTag = pStack.getTag();
            if(compoundTag == null) return;
            if(pEntity instanceof Player){
                Vec3 view = pEntity.getViewVector(1.0f);
                Vec3 view0 = new Vec3(compoundTag.getDouble("lastX"),
                        compoundTag.getDouble("lastY"),
                        compoundTag.getDouble("lastZ"));
                int d = (int)(Math.acos(view.dot(view0) > 1 ? 1 : view.dot(view0))*5);
                compoundTag.putDouble("lastX", view.x);
                compoundTag.putDouble("lastY", view.y);
                compoundTag.putDouble("lastZ", view.z);
                pStack.setDamageValue(pStack.getDamageValue()+d);
            }
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return itemStack.hasTag();
    }

    // TODO: use a item override to do this.
    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round((float)pStack.getDamageValue() * 13.0F / (float)this.getMaxDamage(pStack));
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 64;
    }
}
