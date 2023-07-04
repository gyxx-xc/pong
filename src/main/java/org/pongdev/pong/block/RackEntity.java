package org.pongdev.pong.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import org.pongdev.pong.setup.Registration;

public class RackEntity extends BlockEntity {
    public static final String CONTAIN_CHAMPAGNE = "contain_champagne";

    int containChampagne = 0;

    public RackEntity(BlockPos pPos, BlockState pBlockState) {
        super(Registration.CHAMPAGNE_RACK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        saveClientData(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        loadClientData(pTag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveClientData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null)
            loadClientData(tag);
    }

    private void saveClientData(CompoundTag tag) {
        tag.putInt(CONTAIN_CHAMPAGNE, containChampagne);
    }

    private void loadClientData(CompoundTag tag) {
        if (tag.contains(CONTAIN_CHAMPAGNE))
            containChampagne = tag.getInt(CONTAIN_CHAMPAGNE);
    }

    public void explode(){
        assert this.level != null;
        // open champagne (?)
        this.level.setBlockAndUpdate(worldPosition,
                Registration.SOURCE_CHAMPAGNE.get().defaultFluidState().createLegacyBlock());
        this.level.updateNeighborsAt(worldPosition.below(),
                this.level.getBlockState(worldPosition.below()).getBlock());
    }
}
