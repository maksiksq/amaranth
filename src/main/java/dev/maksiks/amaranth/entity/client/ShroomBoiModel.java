package dev.maksiks.amaranth.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.maksiks.amaranth.entity.custom.ShroomBoiEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ShroomBoiModel<T extends ShroomBoiEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("amaranth", "shroom_boi"), "main");
    private final ModelPart body;
    private final ModelPart legr;
    private final ModelPart hatangietorso;
    private final ModelPart hatahangie;
    private final ModelPart hangies;
    private final ModelPart cap;
    private final ModelPart torso;
    private final ModelPart legl;

    public ShroomBoiModel(ModelPart root) {
        this.body = root.getChild("body");
        this.legr = this.body.getChild("legr");
        this.hatangietorso = this.body.getChild("hatangietorso");
        this.hatahangie = this.hatangietorso.getChild("hatahangie");
        this.hangies = this.hatahangie.getChild("hangies");
        this.cap = this.hatahangie.getChild("cap");
        this.torso = this.hatangietorso.getChild("torso");
        this.legl = this.body.getChild("legl");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(1.0F, 20.0F, 0.5F));

        PartDefinition legr = body.addOrReplaceChild("legr", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = legr.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 39).addBox(1.0F, 0.75F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, -0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r2 = legr.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 40).addBox(1.0F, 0.75F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r3 = legr.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(7, 40).addBox(0.0F, 0.0F, -0.999F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition hatangietorso = body.addOrReplaceChild("hatangietorso", CubeListBuilder.create(), PartPose.offset(1.0F, 2.0F, -1.5F));

        PartDefinition hatahangie = hatangietorso.addOrReplaceChild("hatahangie", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition hangies = hatahangie.addOrReplaceChild("hangies", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = hangies.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.5F, -0.75F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -8.0F, -4.0F, -0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r5 = hangies.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r6 = hangies.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, 7.0F, -0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r7 = hangies.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.0F, 7.0F, -0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r8 = hangies.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 43).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.0F, 6.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r9 = hangies.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -8.0F, -1.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r10 = hangies.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 42).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, -2.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r11 = hangies.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(32, 44).addBox(1.0F, 0.75F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cap = hatahangie.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(38, 33).addBox(-2.0F, -3.0F, -3.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 39).addBox(-4.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 33).addBox(-4.0F, -2.0F, 5.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 35).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -9.0F, 0.0F));

        PartDefinition cube_r12 = cap.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 42).addBox(-4.0F, -1.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r13 = cap.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(24, 42).addBox(-4.0F, -1.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r14 = cap.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(20, 42).addBox(-4.0F, -1.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 0.0F, 9.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r15 = cap.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(16, 42).addBox(-4.0F, -1.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r16 = cap.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -1.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r17 = cap.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(20, 37).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r18 = cap.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(22, 24).addBox(-4.0F, -1.0F, -3.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.0F, -5.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition cube_r19 = cap.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -1.0F, -3.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.0F, 6.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition cube_r20 = cap.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(22, 15).addBox(-4.0F, -1.0F, -3.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r21 = cap.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -1.0F, -3.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition torso = hatangietorso.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.0F, 0.0F));

        PartDefinition cube_r22 = torso.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, -3.0F, 2.0F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 10).addBox(-4.0F, -3.0F, 7.0F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r23 = torso.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 5).addBox(-4.0F, -3.0F, 2.0F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition cube_r24 = torso.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 0).addBox(-4.0F, -3.0F, 2.0F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition cube_r25 = torso.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition legl = body.addOrReplaceChild("legl", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition cube_r26 = legl.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(7, 42).addBox(0.0F, 0.0F, -1.01F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition cube_r27 = legl.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(26, 39).addBox(1.0F, 0.75F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition cube_r28 = legl.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(38, 36).addBox(1.0F, 0.75F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.7854F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -20f, 20f);
        headPitch = Mth.clamp(headPitch, -15f, 20f);

        this.hatangietorso.yRot = headYaw * ((float)Math.PI / 180f);
        this.hatangietorso.xRot = headPitch *  ((float)Math.PI / 180f);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    @Override
    public ModelPart root() {
        return body;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(ShroomBoiAnimations.ANIM_SHROOM_BOI_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, ShroomBoiAnimations.ANIM_SHROOM_BOI_IDLE, ageInTicks, 1f);
    }
}