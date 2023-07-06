package org.pongdev.pong.setup.datagen;

import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.pongdev.pong.Pong;
import org.pongdev.pong.block.ChampagneRack;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.setup.Registration;

public class PongBlockStateProvider extends BlockStateProvider {
    public PongBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Pong.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(Registration.CHAMPAGNE_RACK_BLOCK.get(), new ModelFile.UncheckedModelFile(
                new ResourceLocation(Pong.MODID, "block/"+ChampagneRack.ID) ));
        simpleBlock(Registration.CHAMPAGNE_BOTTLE_BLOCK.get(), new ModelFile.UncheckedModelFile(
                new ResourceLocation(Pong.MODID, "block/"+ ChampagneBottle.ID)
        ));
    }
}
