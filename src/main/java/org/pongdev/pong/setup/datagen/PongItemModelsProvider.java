package org.pongdev.pong.setup.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.pongdev.pong.Pong;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.item.ChampagneSabre;
import org.pongdev.pong.item.Goblet;

public class PongItemModelsProvider extends ItemModelProvider {
    public PongItemModelsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Pong.MODID, helper);
    }

    @Override
    protected void registerModels() {
        basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID))
                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 2)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_0")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 5)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_1")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 10)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_2")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 15)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_3")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 17)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_4")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.POWER_TAG), 19)
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 0)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_5")))
                .end()

                .override()
                .predicate(new ResourceLocation(Pong.MODID, ChampagneBottle.OPEN_TAG), 1)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneBottle.ID + "_open")))
                .end();

        basicItem(new ResourceLocation(Pong.MODID, ChampagneSabre.ID)).override()
                .predicate(new ResourceLocation(Pong.MODID, "damage"), 250)
                .model(basicItem(new ResourceLocation(Pong.MODID, ChampagneSabre.ID + "_broken")))
                .end();

        basicItem(new ResourceLocation(Pong.MODID, Goblet.ID)).override()
                .predicate(new ResourceLocation(Pong.MODID, Goblet.CONTAIN_TAG), 1)
                .model(basicItem(new ResourceLocation(Pong.MODID, Goblet.ID + "_champagne")))
                .end();
    }
}
