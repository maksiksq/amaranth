package dev.maksiks.amaranth.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.custom.ShroomBoiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ShroomBoiRenderer extends MobRenderer<ShroomBoiEntity, ShroomBoiRenderState, ShroomBoiModel> {

    public ShroomBoiRenderer(EntityRendererProvider.Context context) {
        super(context, new ShroomBoiModel(context.bakeLayer(ShroomBoiModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ShroomBoiRenderState createRenderState() {
        return new ShroomBoiRenderState();
    }

    @Override
    public void extractRenderState(ShroomBoiEntity entity, ShroomBoiRenderState state, float partialTick) {
        state.idleAnimationState = entity.idleAnimationState;
        super.extractRenderState(entity, state, partialTick);
    }

    @Override
    public void submit(ShroomBoiRenderState state, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
      if (state.isBaby) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(state, poseStack, nodeCollector, cameraRenderState);
    }


    @Override
    public ResourceLocation getTextureLocation(ShroomBoiRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "textures/entity/shroom_boi/shroom_boi.png");
    }
}
