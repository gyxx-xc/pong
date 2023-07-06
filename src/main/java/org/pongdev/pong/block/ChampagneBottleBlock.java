package org.pongdev.pong.block;

import it.unimi.dsi.fastutil.ints.Int2BooleanFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ChampagneBottleBlock extends Block {
    public static final VoxelShape SHAPE =
            Shapes.join(Block.box(4, 0, 4, 12, 13, 12),
                    Block.box(7, 9, 7, 9, 16, 9), BooleanOp.OR);
    public ChampagneBottleBlock() {
        super(BlockBehaviour.Properties.of()
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
