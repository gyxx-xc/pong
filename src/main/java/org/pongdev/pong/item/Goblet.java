package org.pongdev.pong.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.pongdev.pong.Pong;

public class Goblet extends Item {
    public static final String ID = "goblet";
    private static final String CONTAIN_TAG = "contain";

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
        if (!thisItem.getOrCreateTag().getString(CONTAIN_TAG).equals("")) {
            thisItem.getOrCreateTag().putString(CONTAIN_TAG, "");
            return super.use(pLevel, pPlayer, thisHand); // use as food
        }
        InteractionHand otherHand = thisHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack otherItem = pPlayer.getItemInHand(otherHand);
        if(otherItem.getItem() instanceof ChampagneBottle){
            Pong.LOGGER.info(FluidUtil.getFluidContained(otherItem).toString());
            thisItem.getOrCreateTag().putString(CONTAIN_TAG, "champagne");
            return InteractionResultHolder.success(thisItem);
        }
        return InteractionResultHolder.fail(thisItem);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 5;
    }
}
