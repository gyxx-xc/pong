package org.pongdev.pong.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.pongdev.pong.Pong;

public class PlugRender<T extends Entity> extends EntityRenderer<Entity> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Pong.MODID, "textures/entity/plug.png");
    protected final EntityModel<PlugEntity> model;

    public PlugRender(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new PlugModel<>(pContext.bakeLayer(PlugModel.LAYER_LOCATION));

    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(Entity entityIn, float entityYaw, float partialTicks,
                       PoseStack poseStackIn, MultiBufferSource bufferSourceIn, int packedLightIn) {

        super.render(entityIn, entityYaw, partialTicks, poseStackIn, bufferSourceIn, packedLightIn);
        poseStackIn.pushPose();
        VertexConsumer vertexConsumer = bufferSourceIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(poseStackIn, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStackIn.popPose();
    }

}
