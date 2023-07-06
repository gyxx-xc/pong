package org.pongdev.pong.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.pongdev.pong.setup.Registration;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault // no warning then...
public class ChampagneBottle extends BlockItem {
    public static final String ID = "champagne_bottle";
    public static final String POWER_TAG = "power";
    public static final String OPEN_TAG = "open";
    private static final String X0_TAG = "X0";
    private static final String Y0_TAG = "Y0";
    private static final String Z0_TAG = "Z0";
    public static final String CAPABILITY_TAG = "champagne_capability";

    public ChampagneBottle() {
        super(Registration.CHAMPAGNE_BOTTLE_BLOCK.get(), new Properties());

    }
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag compoundTag = pStack.getTag();
        if(compoundTag == null) return;
        if(compoundTag.getBoolean(OPEN_TAG)) return;
        if(pIsSelected){
            if(pEntity instanceof Player){
                double d = 0;
                if (pLevel.isClientSide) {
                    CompoundTag compoundTag1 = pEntity.getPersistentData();
                    Vec3 view = pEntity.getViewVector(1.0f);
                    Vec3 view0 = new Vec3(compoundTag1.getDouble(X0_TAG),
                            compoundTag1.getDouble(Y0_TAG),
                            compoundTag1.getDouble(Z0_TAG));
                    d = (Math.acos(view.dot(view0) > 1 ? 1 : view.dot(view0)) * 5);
                    d = 0.1 * Math.pow(d, 2) - 0.4;
                    compoundTag1.putDouble(X0_TAG, view.x);
                    compoundTag1.putDouble(Y0_TAG, view.y);
                    compoundTag1.putDouble(Z0_TAG, view.z);
                }
                if(compoundTag.getDouble(POWER_TAG) > 0 || d > 0)
                    compoundTag.putDouble(POWER_TAG, compoundTag.getDouble(POWER_TAG)+d);
                if(compoundTag.getDouble(POWER_TAG) >= 50 && !compoundTag.getBoolean(OPEN_TAG)){
                    OpenChampagne.open(pStack, pEntity, pLevel);
                }
            }
        } else {
            if(compoundTag.getDouble(POWER_TAG) > 0)
                compoundTag.putDouble(POWER_TAG, compoundTag.getDouble(POWER_TAG)-0.4);
        }
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        if(stack.getOrCreateTag().getBoolean(OPEN_TAG))
            return 1;
        else
            return 64;
    }
}
