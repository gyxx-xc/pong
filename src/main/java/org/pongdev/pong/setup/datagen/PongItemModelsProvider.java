package org.pongdev.pong.setup.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.pongdev.pong.Pong;
import org.pongdev.pong.item.Champagne;
import org.pongdev.pong.setup.Registration;

public class PongItemModelsProvider extends ItemModelProvider {
    public PongItemModelsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Pong.MODID, helper);
    }

    @Override
    protected void registerModels() {
        basicItem(new ResourceLocation(Pong.MODID, Champagne.ID))
                .override()
                .predicate(new ResourceLocation(Pong.MODID, "power"), 16)
                .model(basicItem(new ResourceLocation(Pong.MODID, Champagne.ID+"_1")))
                .end();
    }
}
