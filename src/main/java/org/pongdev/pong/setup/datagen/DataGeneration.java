package org.pongdev.pong.setup.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import org.pongdev.pong.Pong;

import java.util.concurrent.CompletableFuture;

public class DataGeneration {

    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new PongChineseLanguageProvider(packOutput));
        generator.addProvider(event.includeClient(), new PongItemModelsProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new PongBlockStateProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new PongEnglishLanguageProvider(packOutput));
        generator.addProvider(event.includeClient(), new PongRecipeProvider(packOutput));

    }
}
