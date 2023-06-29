package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.pongdev.pong.setup.Registration;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault // no warning then...
public class Champagne extends Item {
    public static final String ID = "champagne";
    public static final String POWER_TAG = "power";
    public static final String OPEN_TAG = "open";
    private static final String X0_TAG = "X0";
    private static final String Y0_TAG = "Y0";
    private static final String Z0_TAG = "Z0";

    private final MobEffectInstance effectInstance = new MobEffectInstance(Registration.DRUNK.get(), 500);

    public Champagne() {
        super(new Item.Properties());
    }
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag compoundTag = pStack.getTag();
        if(compoundTag == null) return;
        if(compoundTag.getBoolean(OPEN_TAG)) return;
        if(pIsSelected){
            if(pEntity instanceof Player){
                Vec3 view = pEntity.getViewVector(1.0f);
                Vec3 view0 = new Vec3(compoundTag.getDouble(X0_TAG),
                        compoundTag.getDouble(Y0_TAG),
                        compoundTag.getDouble(Z0_TAG));
                double d = (Math.acos(view.dot(view0) > 1 ? 1 : view.dot(view0))*5);
                d -= 0.4;
                compoundTag.putDouble(X0_TAG, view.x);
                compoundTag.putDouble(Y0_TAG, view.y);
                compoundTag.putDouble(Z0_TAG, view.z);
                if(compoundTag.getDouble(POWER_TAG) > 0 || d > 0)
                    compoundTag.putDouble(POWER_TAG, compoundTag.getDouble(POWER_TAG)+d);
                if(compoundTag.getDouble(POWER_TAG) >= 20 && !compoundTag.getBoolean(OPEN_TAG)){
                    OpenChampagne.open(pStack, pEntity, pLevel);
                    compoundTag.putBoolean(OPEN_TAG, true);
                }
            }
        } else {
            if(compoundTag.getDouble(POWER_TAG) > 0)
                compoundTag.putDouble(POWER_TAG, compoundTag.getDouble(POWER_TAG)-0.4);
        }
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 64;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (itemstack.getOrCreateTag().getBoolean(OPEN_TAG)) {
            return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
        } else {
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 15;
    }

    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        pEntityLiving.addEffect(new MobEffectInstance(effectInstance));
        return pStack;
    }
}
