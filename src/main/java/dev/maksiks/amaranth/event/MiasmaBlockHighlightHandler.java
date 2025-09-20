package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;

@EventBusSubscriber(modid = Amaranth.MOD_ID, value = Dist.CLIENT)
public class MiasmaBlockHighlightHandler {

    @SubscribeEvent
    public static void onDrawBlockHighlight(RenderHighlightEvent.Block event) {
        BlockHitResult hit = event.getTarget();
        BlockPos pos = hit.getBlockPos();

        Level level = event.getCamera().getEntity().level();
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlocks.MIASMA_ICE.get())) {
            event.setCanceled(true);
        }


    }
}
