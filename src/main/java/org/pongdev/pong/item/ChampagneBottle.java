package org.pongdev.pong.item;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.pongdev.pong.block.RackEntity;
import org.pongdev.pong.particle.ModParticles;
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
                if(compoundTag.getDouble(POWER_TAG) > 0 || d > 0)
                    compoundTag.putDouble(POWER_TAG, compoundTag.getDouble(POWER_TAG)+d);
                if(compoundTag.getDouble(POWER_TAG) >= 50 && !compoundTag.getBoolean(OPEN_TAG)){
                    if(pStack.getCount() > 1) {
                        for (int i = 0; i < pStack.getCount() - 1; i ++){
                            ItemStack itemStack = new ItemStack(pStack.getItem(), 1, pStack.getTag());
                            OpenChampagne.open(itemStack, pEntity, pLevel);
                            Player player = (Player) pEntity;
                            if (!player.getInventory().add(itemStack))
                                player.drop(itemStack, false);
                        }
                    }
                    pStack.setCount(1);
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

    private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                pContext.getLevel().addParticle(ModParticles.SPLASH_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            spawnFoundParticles(pContext, positionClicked);
        }
        if (!pContext.getItemInHand().getOrCreateTag().getBoolean(OPEN_TAG)) {
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof RackEntity rack) {
                rack.getPersistentData().putInt("number", 1);
                pContext.getItemInHand().shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(pContext);
    }
}
