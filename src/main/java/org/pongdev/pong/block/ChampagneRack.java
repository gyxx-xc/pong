package org.pongdev.pong.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.pongdev.pong.setup.Registration;

public class ChampagneRack extends HorizontalDirectionalBlock implements EntityBlock {
    public static final String ID = "champagne_rack";
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final String CONTAIN = "contain_number";
    public static final VoxelShape SHAPE_EMPTY_ZP = Block.box(0, 0, 10, 16, 16, 13);
    public static final VoxelShape SHAPE_EMPTY_ZN = Block.box(0, 0, 3, 16, 16, 6);
    public static final VoxelShape SHAPE_EMPTY_XP = Block.box(10, 0, 0, 13, 16, 16);
    public static final VoxelShape SHAPE_EMPTY_XN = Block.box(3, 0, 0, 6, 16, 16);
    public static final VoxelShape SHAPE_BOTTLE = Block.box(0, 0, 0, 16, 16, 16);

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

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockEntity block = pLevel.getBlockEntity(pPos);
        if (block == null) return super.getShape(pState, pLevel, pPos, pContext);
        if (block.getPersistentData().getInt(CONTAIN) != 0)
            return SHAPE_BOTTLE;
        else
            switch (pState.getValue(FACING)) {
                case NORTH -> {
                    return SHAPE_EMPTY_ZN;
                }
                case SOUTH -> {
                    return SHAPE_EMPTY_ZP;
                }
                case WEST -> {
                    return SHAPE_EMPTY_XN;
                }
                case EAST -> {
                    return SHAPE_EMPTY_XP;
                }
                default -> {
                    return super.getShape(pState, pLevel, pPos, pContext);
                }
            }
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

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        BlockEntity block = pLevel.getBlockEntity(pHit.getBlockPos());
        if (block instanceof RackEntity rack) {
            if (rack.getPersistentData().getInt(CONTAIN) > 0)
                rack.explode();
        }
    }
}
