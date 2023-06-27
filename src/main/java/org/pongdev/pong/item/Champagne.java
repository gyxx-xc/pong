package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault // no warning then...
public class Champagne extends Item {
    public static final String ID = "champagne";
    public Champagne() {
        super(new Item.Properties().durability(1000));
    }
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remain) {
        CompoundTag compoundTag = itemStack.getTag();
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
                compoundTag.putDouble("power", compoundTag.getDouble("power")+d);
            }
        }
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 64;
    }
}
