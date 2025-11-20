package dev.maksiks.amaranth.worldgen.features.structure_processor;

import com.mojang.serialization.MapCodec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;

import static dev.maksiks.amaranth.worldgen.features.structure_processor.ModStructureProcessorTypes.GIGANTIC_SATISTREE_PROCESSOR;

public class GiganticSatistreeStructureProcessor extends StructureProcessor {
    public static final GiganticSatistreeStructureProcessor INSTANCE = new GiganticSatistreeStructureProcessor();

    public static final MapCodec<GiganticSatistreeStructureProcessor> CODEC =
            MapCodec.unit(() -> INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(
            LevelReader level,
            BlockPos worldPos,
            BlockPos localPos,
            StructureTemplate.StructureBlockInfo original,
            StructureTemplate.StructureBlockInfo placed,
            StructurePlaceSettings settings,
            StructureTemplate template
    ) {
        RandomSource random = settings.getRandom(worldPos);

        // slightly dithering the leaves
        // failed to implement a leaf decay check for it, would be more fun to make it like 75%
        if (placed.state().is(ModBlocks.SATISTREE_LOG.get())) return placed;
        if (random.nextInt(100) < 95) {
            return placed;
        } else {
            return null;
        }
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return GIGANTIC_SATISTREE_PROCESSOR.get();
    }
}
