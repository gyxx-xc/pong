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
        // 第一个参数为模型对应的物品 ID
        // 第二个参数为父模型，一般物品的父模型均为 minecraft:item/generated，此处简写为 new ResourceLocation("item/generated")
        // 第三个参数及第四个参数为纹理名称及位置，对于当前父模型而言需要指定 layer0 对应的纹理名称
        singleTexture(Champagne.ID,
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(Pong.MODID, "item/"+ Champagne.ID));
    }
}
