package org.pongdev.pong.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ChampagneRack extends Block implements EntityBlock {
    public static final String ID = "champagne_rack";

    public ChampagneRack() {
        super(BlockBehaviour.Properties.of()
                .strength(1.0F)
                .sound(SoundType.WOOD)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RackEntity(pPos, pState);
    }
}
