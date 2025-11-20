package dev.maksiks.amaranth.worldgen.features.tree;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.worldgen.features.structure_processor.GiganticSatistreeStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;

import static dev.maksiks.amaranth.worldgen.features.ModFeatureUtils.wouldDecay;

public class GiganticSatistreeFeature extends Feature<NoneFeatureConfiguration> {

    private static final int CANOPY_VARIANT_COUNT = 2;
    private static final int VERTICAL_OFFSET = -25;
    private static final int X_OFFSET = 0;
    private static final int Z_OFFSET = 0;
    private static final int BASE_HEIGHT = 40;
    private static final List<ResourceLocation> CANOPY_STRUCTURES = createCanopyList();

    private static List<ResourceLocation> createCanopyList() {
        List<ResourceLocation> structures = new ArrayList<>();
        for (int i = 1; i <= CANOPY_VARIANT_COUNT; i++) {
            structures.add(ResourceLocation.fromNamespaceAndPath("amaranth", "gigantic_satistree/gigantic_satistree_canopy_" + i));
        }
        return structures;
    }

    public GiganticSatistreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockState logState = ModBlocks.SATISTREE_LOG.get().defaultBlockState();

        setDirt(level, origin.below());

        int freeTreeHeight = BASE_HEIGHT + random.nextInt(4);
        if (!hasVerticalSpace(level, origin, freeTreeHeight)) {
            return false;
        }
        int limit = 6;
        Direction highDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        List<BlockPos> roots = new ArrayList<>();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos placePos = origin.relative(dir);

            roots.add(placePos);
            roots.add(placePos.above());

            BlockPos maybeAirPos = placePos.below();
            limit--;
            int airIx = 0;

            while (!level.getBlockState(maybeAirPos).isSolid()) {
                airIx++;
                roots.add(maybeAirPos);
                maybeAirPos = maybeAirPos.below();
                if (airIx > limit) break;
            }

            if (level.getBlockState(maybeAirPos).is(Blocks.GRASS_BLOCK)) {
                setDirt(level, maybeAirPos);
            }
            if (level.getBlockState(placePos.below()).is(Blocks.GRASS_BLOCK)) {
                setDirt(level, placePos.below());
            }

            if (dir == highDir) {
                roots.add(origin.relative(highDir.getClockWise()).above(2));
                roots.add(origin.relative(highDir).above(2));
                roots.add(origin.relative(highDir).above(3));
            }
        }

        BlockPos canopyPos = origin.above(freeTreeHeight);

        ResourceLocation selectedCanopy = CANOPY_STRUCTURES.get(random.nextInt(CANOPY_STRUCTURES.size()));

        boolean success = placeCanopyStructure(level, canopyPos, selectedCanopy, random);
        if (!success) return false;

        for (BlockPos rootPos : roots) {
            this.safeSetBlock(level, rootPos, logState);
        }

        int randomBranchCount = random.nextInt(4) + 1;
        for (int i = 0; i < freeTreeHeight; i++) {
            this.safeLeafReplacingSetBlock(level, origin.above(i), logState);
        }

        // very readable i know
        // placing branches, starting at the start of the leaves so they're hidden
        // reused the same branches from anthocyanin but they're inside the leaves so they work here just fine
        // that 5 accounts for the leaves on top having a lot of open space so stuff might stick out
        while (randomBranchCount > 0) {
            for (int i = freeTreeHeight+VERTICAL_OFFSET; i < freeTreeHeight-5; i++) {
                if (random.nextInt(100) < 15) {
                    randomBranchCount--;

                    for (int j = 0; j < 2 + random.nextInt(2); j++) {
                        double horizontalAngle = random.nextDouble() * Math.PI * 2.0D;
                        int length = 2 + random.nextInt(3);
                        double tilt = Math.toRadians(random.nextDouble() * 50.0D + 15.0D);

                        double xDir = Math.cos(horizontalAngle) * Math.cos(tilt);
                        double yDir = Math.sin(tilt);
                        double zDir = Math.cos(horizontalAngle) * Math.sin(tilt);

                        for (int k = 0; k < length; k++) {
                            BlockPos placementPos = origin.above(i);
                            int x = placementPos.getX() + (int) Math.round(xDir * k);
                            int y = placementPos.getY() + (int) Math.round(yDir * k);
                            int z = placementPos.getZ() + (int) Math.round(zDir * k);
                            this.safeLeafReplacingSetBlock(level, new BlockPos(x, y, z), logState);
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean placeCanopyStructure(WorldGenLevel level, BlockPos pos, ResourceLocation structureLocation, RandomSource random) {
        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();
        StructureTemplate template = templateManager.getOrCreate(structureLocation);

        if (template.getSize().getX() == 0 || template.getSize().getZ() == 0) {
            Amaranth.LOGGER.error("Amaranth: Failed to load gigantic satistree structure: {}", structureLocation);
            return false;
        }

        Rotation rotation = Rotation.getRandom(random);
        Mirror mirror = Mirror.NONE;

        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setRotation(rotation)
                .setMirror(mirror)
                .setIgnoreEntities(false)
                .setRandom(random)
                .addProcessor(GiganticSatistreeStructureProcessor.INSTANCE);
        // custom processor for random chances and whatever else i'll add in the next 5 minutes before i starve
        // i mean, go to the fridge

        BlockPos intendedCenter = pos.above(VERTICAL_OFFSET)
                .relative(Direction.EAST, X_OFFSET)
                .relative(Direction.NORTH, Z_OFFSET);

        int sizeX = template.getSize().getX();
        int sizeZ = template.getSize().getZ();

        int localCenterX = sizeX / 2;
        int localCenterZ = sizeZ / 2;

        BlockPos transformedCenterLocal = transformLocalByMirrorRotation(localCenterX, 0, localCenterZ, sizeX, sizeZ, mirror, rotation);

        BlockPos targetOrigin = intendedCenter.offset(-transformedCenterLocal.getX(), -transformedCenterLocal.getY(), -transformedCenterLocal.getZ());

        BlockPos realStart = template.getZeroPositionWithTransform(targetOrigin, mirror, rotation);

        // accounting for odd size
        BlockPos rotationOffset = switch (rotation) {
            case NONE -> new BlockPos(0, 0, 1);
            case CLOCKWISE_90 -> new BlockPos(-1, 0, 0);
            case CLOCKWISE_180 -> new BlockPos(0, 0, -1);
            case COUNTERCLOCKWISE_90 -> new BlockPos(1, 0, 0);
        };

        realStart = realStart.offset(rotationOffset);

        // bailing if not enough space (it's a sapling after all)
        if (!hasCanopySpace(level, realStart, template, rotation)) {
            return false;
        }

        return template.placeInWorld(level, realStart, realStart, settings, random, 2);
    }

    private BlockPos transformLocalByMirrorRotation(int x, int y, int z, int sizeX, int sizeZ, Mirror mirror, Rotation rotation) {
        if (mirror == net.minecraft.world.level.block.Mirror.FRONT_BACK) {
            z = sizeZ - 1 - z;
        } else if (mirror == net.minecraft.world.level.block.Mirror.LEFT_RIGHT) {
            x = sizeX - 1 - x;
        }

        return switch (rotation) {
            case NONE -> new BlockPos(x, y, z);
            case CLOCKWISE_90 -> new BlockPos(sizeZ - 1 - z, y, x);
            case CLOCKWISE_180 -> new BlockPos(sizeX - 1 - x, y, sizeZ - 1 - z);
            case COUNTERCLOCKWISE_90 -> new BlockPos(z, y, sizeX - 1 - x);
        };
    }

    private void setDirt(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT)) {
            this.safeSetBlock(level, pos, Blocks.DIRT.defaultBlockState());
        }
    }

    private void safeSetBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        BlockState existing = level.getBlockState(pos);

        if (existing.canBeReplaced()) {
            this.setBlock(level, pos, state);
        }
    }

    private void safeLeafReplacingSetBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        BlockState existing = level.getBlockState(pos);

        if (existing.is(BlockTags.LEAVES) || existing.canBeReplaced()) {
            this.setBlock(level, pos, state);
        }
    }

    private boolean hasVerticalSpace(WorldGenLevel level, BlockPos origin, int height) {
        for (int i = 1; i <= height; i++) {
            BlockPos check = origin.above(i);
            if (!level.getBlockState(check).canBeReplaced()) {
                return false;
            }
        }
        return true;
    }

    // im only half sure this check works properly
    private boolean hasCanopySpace(WorldGenLevel level, BlockPos start, StructureTemplate template,
                                   Rotation rotation) {
        BlockPos size = getRotatedSize(template, rotation);
        int sx = size.getX();
        int sy = size.getY();
        int sz = size.getZ();

        for (int x = 0; x < sx; x++) {
            for (int y = 0; y < sy; y++) {
                for (int z = 0; z < sz; z++) {
                    BlockPos check = start.offset(x, y, z);
                    BlockState state = level.getBlockState(check);

                    if (!state.canBeReplaced()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private static BlockPos getRotatedSize(StructureTemplate template, Rotation rotation) {
        Vec3i s = template.getSize();
        return switch (rotation) {
            case NONE, CLOCKWISE_180 -> new BlockPos(s.getX(), s.getY(), s.getZ());
            case CLOCKWISE_90, COUNTERCLOCKWISE_90 -> new BlockPos(s.getZ(), s.getY(), s.getX());
        };
    }

    // "decaying" leaves
    // uses the same math as above oh god this class is complicated as hell
//    // BROKEN !!!
//    private void postProcessLeaves(WorldGenLevel level, BlockPos canopyPos, ResourceLocation structureLocation, RandomSource random) {
//        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();
//        StructureTemplate template = templateManager.getOrCreate(structureLocation);
//
//        Mirror mirror = Mirror.NONE;
//
//        BlockPos intendedCenter = canopyPos.above(VERTICAL_OFFSET)
//                .relative(Direction.EAST, X_OFFSET)
//                .relative(Direction.NORTH, Z_OFFSET);
//
//        BlockPos size = new BlockPos(template.getSize());
//        int cx = size.getX() / 2;
//        int cz = size.getZ() / 2;
//
//        BlockPos transformed = transformLocalByMirrorRotation(cx, 0, cz, size.getX(), size.getZ(), mirror, rotation);
//        BlockPos start = intendedCenter.offset(-transformed.getX(), -transformed.getY(), -transformed.getZ());
//        BlockPos realStart = template.getZeroPositionWithTransform(start, mirror, rotation);
//
//        BlockPos rotationOffset = switch (rotation) {
//            case NONE -> new BlockPos(0, 0, 1);
//            case CLOCKWISE_90 -> new BlockPos(-1, 0, 0);
//            case CLOCKWISE_180 -> new BlockPos(0, 0, -1);
//            case COUNTERCLOCKWISE_90 -> new BlockPos(1, 0, 0);
//        };
//
//        realStart = realStart.offset(rotationOffset);
//
//        BlockPos rotatedSize = getRotatedSize(template, rotation);
//
//        for (int x = 0; x < rotatedSize.getX(); x++) {
//            for (int y = 0; y < rotatedSize.getY(); y++) {
//                for (int z = 0; z < rotatedSize.getZ(); z++) {
//
//                    BlockPos check = realStart.offset(x, y, z);
//                    BlockState state = level.getBlockState(check);
//
//                    if (state.is(BlockTags.LEAVES)) {
//                        if (wouldDecay(level, check, state)) {
//                            level.setBlock(check, Blocks.AIR.defaultBlockState(), 2);
//                        }
//                    }
//                }
//            }
//        }
//    }
}