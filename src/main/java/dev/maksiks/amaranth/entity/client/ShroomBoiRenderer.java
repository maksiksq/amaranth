package dev.maksiks.amaranth.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.custom.ShroomBoiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShroomBoiRenderer extends MobRenderer<ShroomBoiEntity, ShroomBoiModel<ShroomBoiEntity>> {

    public ShroomBoiRenderer(EntityRendererProvider.Context context) {
        super(context, new ShroomBoiModel<>(context.bakeLayer(ShroomBoiModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShroomBoiEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "textures/entity/shroom_boi/shroom_boi.png");
    }

    @Override
    public void render(ShroomBoiEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
