package org.pongdev.pong.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.pongdev.pong.setup.Registration;

public class ChampagneRack extends HorizontalDirectionalBlock implements EntityBlock {
    public static final String ID = "champagne_rack";
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final String CONTAIN = "contain_number";

    public ChampagneRack() {
        super(BlockBehaviour.Properties.of()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RackEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        CompoundTag tag = pLevel.getBlockEntity(pPos).getPersistentData();
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!itemStack.isEmpty()) return InteractionResult.FAIL;
        if (tag.getInt(CONTAIN) <= 0) return InteractionResult.FAIL;
        if (itemStack.isEmpty()) {
            ItemStack newItemStack = new ItemStack(Registration.CHAMPAGNE.get());
            pPlayer.getInventory().add(newItemStack);
            tag.putInt(CONTAIN, tag.getInt(CONTAIN)-1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
