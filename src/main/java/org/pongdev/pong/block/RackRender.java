package org.pongdev.pong.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.DisplayRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelDataManager;
import net.minecraftforge.client.model.data.ModelProperty;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.pongdev.pong.item.ChampagneBottle;
import org.pongdev.pong.setup.Registration;

public class RackRender implements BlockEntityRenderer<RackEntity> {
    public RackRender(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RackEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1, 0.5);
        pPoseStack.mulPose( pBlockEntity.getBlockState().getValue(ChampagneRack.FACING).getRotation() );
        pPoseStack.translate(-0.5, -0.5, 0);

        int number = pBlockEntity.getPersistentData().getInt(ChampagneRack.CONTAIN);
        int[][] place = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int i = 0; i < number; i++) {
            pPoseStack.pushPose();
            pPoseStack.translate(place[i][0]*0.25, 0, place[i][1]*0.25);
            BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
            blockRenderDispatcher.renderSingleBlock(Registration.CHAMPAGNE_BOTTLE_BLOCK.get().defaultBlockState(),
                    pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
            pPoseStack.popPose();
        }
        pPoseStack.popPose();
    }
}
