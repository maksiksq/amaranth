package dev.maksiks.amaranth.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@EventBusSubscriber(modid = Amaranth.MOD_ID, value = Dist.CLIENT)
public class MiasmaBlockHighlightHandler {

    @SubscribeEvent
    public static void onDrawBlockHighlight(RenderHighlightEvent.Block event) {
        BlockHitResult hit = event.getTarget();
        BlockPos pos = hit.getBlockPos();

        Level level = event.getCamera().getEntity().level();
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlocks.MIASMA_ICE.get()) || state.is(ModBlocks.DENSE_MIASMA_ICE.get())) {
            event.setCanceled(true);
            drawCustomOutline(event, pos);
        }
    }


    private static void drawCustomOutline(RenderHighlightEvent.Block event, BlockPos pos) {
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource buffer = event.getMultiBufferSource();
        Camera camera = event.getCamera();

        double camX = camera.getPosition().x;
        double camY = camera.getPosition().y;
        double camZ = camera.getPosition().z;

        poseStack.pushPose();
        poseStack.translate(pos.getX() - camX, pos.getY() - camY, pos.getZ() - camZ);

        VertexConsumer consumer = buffer.getBuffer(RenderType.lines());
        drawCubeOutline(poseStack, consumer);

        poseStack.popPose();
    }

    private static void drawCubeOutline(PoseStack poseStack, VertexConsumer consumer) {
        Matrix4f mat = poseStack.last().pose();

        Vector3f[] corners = new Vector3f[]{
                new Vector3f(0f, 0f, 0f), new Vector3f(1f, 0f, 0f), new Vector3f(1f, 1f, 0f), new Vector3f(0f, 1f, 0f),
                new Vector3f(0f, 0f, 1f), new Vector3f(1f, 0f, 1f), new Vector3f(1f, 1f, 1f), new Vector3f(0f, 1f, 1f)
        };

        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0},
                {4, 5}, {5, 6}, {6, 7}, {7, 4},
                {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        float r = 0.0f, g = 0.0f, b = 0.0f, a = 1.0f;

        Vector3f normal = new Vector3f(0, 1, 0);

        for (int[] edge : edges) {
            Vector3f start = corners[edge[0]];
            Vector3f end = corners[edge[1]];

            consumer.addVertex(mat, start.x(), start.y(), start.z())
                    .setColor(r, g, b, a)
                    .setNormal(normal.x(), normal.y(), normal.z());

            consumer.addVertex(mat, end.x(), end.y(), end.z())
                    .setColor(r, g, b, a)
                    .setNormal(normal.x(), normal.y(), normal.z());
        }
    }
}
