package org.pongdev.pong.block;

import it.unimi.dsi.fastutil.ints.Int2BooleanFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ChampagneBottleBlock extends FallingBlock {
    public static final VoxelShape SHAPE =
            Shapes.join(Block.box(5.25, 0, 5.25, 10.75, 8, 10.75),
                    Block.box(7, 9, 7, 9, 16, 9), BooleanOp.OR);
    public ChampagneBottleBlock() {
        super(BlockBehaviour.Properties.of()
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void falling(FallingBlockEntity pEntity) {
        pEntity.dropItem = false;
    }

    @Override
    public void onBrokenAfterFall(Level pLevel, BlockPos pPos, FallingBlockEntity pFallingBlock) {
        // TODO: play sound of braking glass
        // TODO: make the cloud
        super.onBrokenAfterFall(pLevel, pPos, pFallingBlock);
    }
}
