package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class OrderlyCourtsRuins extends Feature<NoneFeatureConfiguration> {

    public OrderlyCourtsRuins(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    // Chance = tier's chance - last one
    // also im one step away from making a gacha game again help
    private enum StructureRarity {
        COMMON(1),
        UNCOMMON(2),
        RARE(3),
        EPIC(4),
        LEGENDARY(90);

        private final int cumulativeWeight;

        StructureRarity(int cumulativeWeight) {
            this.cumulativeWeight = cumulativeWeight;
        }

        public static StructureRarity getRandomRarity(RandomSource random) {
            int roll = random.nextInt(100);
            for (StructureRarity rarity : values()) {
                if (roll < rarity.cumulativeWeight) {
                    return rarity;
                }
            }
            return COMMON;
        }
    }

    // Structure lists for each rarity tier
    private static final List<ResourceLocation> COMMON_STRUCTURES = List.of(
            // TODO: Add your common structure NBT file locations
            // ResourceLocation.fromNamespaceAndPath("amaranth", "structures/common_pile"),
            // ResourceLocation.fromNamespaceAndPath("amaranth", "structures/common_rock"),
            // ResourceLocation.fromNamespaceAndPath("amaranth", "structures/common_shrine")
    );

    private static final List<ResourceLocation> UNCOMMON_STRUCTURES = List.of(
            //
    );

    private static final List<ResourceLocation> RARE_STRUCTURES = List.of(
            //
    );

    private static final List<ResourceLocation> EPIC_STRUCTURES = List.of(
            //
    );

    private static final List<ResourceLocation> LEGENDARY_STRUCTURES = List.of(
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_duck"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_2")
    );

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();

        if (!canPlaceStructureAt(level, origin)) {
            return false;
        }

        // roll 1
        StructureRarity rarity = StructureRarity.getRandomRarity(random);

        // roll 2
        List<ResourceLocation> structureList = getStructureList(rarity);
        if (structureList.isEmpty()) {
            return false;
        }

        ResourceLocation selectedStructure = structureList.get(random.nextInt(structureList.size()));

        return placeStructureTemplate(level, origin, selectedStructure, random);
    }

    private List<ResourceLocation> getStructureList(StructureRarity rarity) {
        return switch (rarity) {
            case COMMON -> COMMON_STRUCTURES;
            case UNCOMMON -> UNCOMMON_STRUCTURES;
            case RARE -> RARE_STRUCTURES;
            case EPIC -> EPIC_STRUCTURES;
            case LEGENDARY -> LEGENDARY_STRUCTURES;
        };
    }

    // the placer
    private boolean placeStructureTemplate(WorldGenLevel level, BlockPos origin, ResourceLocation structureLocation, RandomSource random) {
        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();

        StructureTemplate template = templateManager.getOrCreate(structureLocation);
        // randomly rotating because why not
        Rotation rotation = Rotation.getRandom(random);

        ChunkPos chunkPos = new ChunkPos(origin);
        BoundingBox boundingBox = new BoundingBox(
                chunkPos.getMinBlockX() - 16,
                level.getMinBuildHeight(),
                chunkPos.getMinBlockZ() - 16,
                chunkPos.getMaxBlockX() + 16,
                level.getMaxBuildHeight(),
                chunkPos.getMaxBlockZ() + 16
        );

        StructurePlaceSettings placeSettings = new StructurePlaceSettings()
                .setRotation(rotation)
                .setBoundingBox(boundingBox)
                .setRandom(random);

        Vec3i structureSize = template.getSize(rotation);
        BlockPos centeredPos = origin.offset(-structureSize.getX() / 2, 0, -structureSize.getZ() / 2);

        BlockPos finalPos = template.getZeroPositionWithTransform(centeredPos, Mirror.NONE, rotation);
        template.placeInWorld(level, finalPos, finalPos, placeSettings, random, 4);

        return true;
    }

    private static boolean canPlaceStructureAt(WorldGenLevel level, BlockPos pos) {
        BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);

        // above bow'lho'volher check
        if (level.getFluidState(surfacePos).is(FluidTags.WATER) ||
                level.getFluidState(surfacePos.above()).is(FluidTags.WATER)) {
            return false;
        }

        // near bow'lho'volher check
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos checkPos = surfacePos.offset(x, 0, z);
                if (level.getFluidState(checkPos).is(FluidTags.WATER)) {
                    return false;
                }
            }
        }

        // sea level and space check
        int y = surfacePos.getY();
        if (y < level.getSeaLevel() - 10 || y > level.getSeaLevel() + 100) {
            return false;
        }

        // slope check
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                int height = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, checkPos).getY();
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        // (arbitrary)
        if (maxHeight - minHeight > 8) {
            return false;
        }

        // above trees check
        BlockState groundBlock = level.getBlockState(surfacePos.below());
        if (!groundBlock.isSolid() || groundBlock.is(BlockTags.LEAVES)) {
            return false;
        }

        // air above check
        for (int i = 1; i <= 10; i++) {
            BlockPos airPos = surfacePos.above(i);
            BlockState state = level.getBlockState(airPos);
            if (!state.isAir() && !state.is(BlockTags.REPLACEABLE)) {
                return false;
            }
        }

        // floatie check
        for (int i = 1; i <= 3; i++) {
            BlockPos belowPos = surfacePos.below(i);
            if (level.getBlockState(belowPos).isAir()) {
                return false;
            }
        }

        return true;
    }
}