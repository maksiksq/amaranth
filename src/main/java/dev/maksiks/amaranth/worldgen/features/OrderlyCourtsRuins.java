package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SupportType;
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
        COMMON(55),
        UNCOMMON(20),
        RARE(15),
        EPIC(9),
        LEGENDARY(1);

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
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_arch_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_arch_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_fountain"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_lunch_table_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_lunch_table_3"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_3"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_4"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_5"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_6"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_build_7"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_fallen_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_fallen_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_fallen_3"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_fallen_4"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_fallen_5"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_pillar_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_pillar_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_pillar_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_pillar_3"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_pillar_4"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_ruined_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_pillar_ruined_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_1"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_2"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_3"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_4"),
            //
            //
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_7"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_8"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_9"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_10"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_11"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_12"),
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_thin_pillar_13")
    );

    // i never ended up using these but i think they're just left as empties which is not bad either
    private static final List<ResourceLocation> UNCOMMON_STRUCTURES = List.of(
            //
    );

    // i never ended up using these but they're just left as empties which is not bad either
    private static final List<ResourceLocation> RARE_STRUCTURES = List.of(
            //
    );

    private static final List<ResourceLocation> EPIC_STRUCTURES = List.of(
            ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_duck")
    );

    // i never ended up using these but they're just left as empties which is not bad either
    private static final List<ResourceLocation> LEGENDARY_STRUCTURES = List.of();

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

        if (template.getSize().getX() == 0 || template.getSize().getZ() == 0) {
            Amaranth.LOGGER.error("Heat death of structure: {}", structureLocation);
            return false;
        }

        Rotation rotation = Rotation.getRandom(random);
        Vec3i structureSize = template.getSize(rotation);
        BlockPos centeredPos = origin.offset(-structureSize.getX() / 2, 0, -structureSize.getZ() / 2);
        BlockPos finalPos = template.getZeroPositionWithTransform(centeredPos, Mirror.NONE, rotation);

        // moving the fountains 1 block down because they're the only ones to generate with a base
        if (structureLocation.equals(ResourceLocation.fromNamespaceAndPath("amaranth", "orderly_ruins/orderly_fountain"))) {
            finalPos = finalPos.below(1);
        }

        if (!hasValidFoundation(level, finalPos, structureSize)) {
            return false;
        }

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

        template.placeInWorld(level, finalPos, finalPos, placeSettings, random, 4);
        return true;
    }

    private boolean hasValidFoundation(WorldGenLevel level, BlockPos pos, Vec3i structureSize) {
        for (int x = 0; x < structureSize.getX(); x++) {
            for (int z = 0; z < structureSize.getZ(); z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                BlockPos groundPos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, checkPos);

                // it's deprecated but i have 0 idea what to replace it with
                BlockPos belowPos = groundPos.below();
                BlockState groundBlock = level.getBlockState(belowPos);
                if (!groundBlock.isFaceSturdy(level, belowPos, Direction.UP, SupportType.FULL)) {
                    return false;
                }

                // a bit of variation is ok
                int heightDiff = Math.abs(groundPos.getY() - pos.getY());
                if (heightDiff > 3) {
                    return false;
                }
            }
        }
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

        // .getSeaLevel() is deprecated but i have no idea what to replace it with
        // maybe it's just a solid 63? Either way let it be for now
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
        BlockPos surfaceBelowPos = surfacePos.below();
        BlockState groundBlock = level.getBlockState(surfaceBelowPos);
        if (!groundBlock.isFaceSturdy(level, surfaceBelowPos, Direction.UP, SupportType.FULL) || groundBlock.is(BlockTags.LEAVES)) {
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