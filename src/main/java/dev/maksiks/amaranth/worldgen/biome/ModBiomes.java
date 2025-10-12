package dev.maksiks.amaranth.worldgen.biome;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.sound.ModSounds;
import dev.maksiks.amaranth.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import terrablender.api.ParameterUtils;

import javax.annotation.Nullable;

public class ModBiomes {
    public static final ResourceKey<Biome> TEST_BIOME = register("test_biome");
    public static final ResourceKey<Biome> MYSTIC_FOREST = register("mystic_forest");
    public static final ResourceKey<Biome> STUBBY_WOODLAND = register("stubby_woodland");
    public static final ResourceKey<Biome> SILVER_BIRCH_FOREST = register("silver_birch_forest");
    public static final ResourceKey<Biome> DESOLATE_ICE_FIELDS = register("desolate_ice_fields");
    public static final ResourceKey<Biome> MIXED_WOODS = register("mixed_woods");
    public static final ResourceKey<Biome> ORDERLY_COURTS = register("orderly_courts");
    public static final ResourceKey<Biome> ORDERLY_COURTS_RUINS = register("orderly_courts_ruins");
    public static final ResourceKey<Biome> TREE_ON_TREE_FOREST = register("tree_on_tree_forest");
    public static final ResourceKey<Biome> SHROOMLANDS = register("shroomlands");
    public static final ResourceKey<Biome> DUSTY_FLATS = register("dusty_flats");
    public static final ResourceKey<Biome> ANTHOCYANIN_FOREST = register("anthocyanin_forest");
    public static final ResourceKey<Biome> FIELDS_OF_PAIN = register("fields_of_pain");
    public static final ResourceKey<Biome> THRUMLETONS = register("thrumletons");
//    public static final ResourceKey<Biome> MUSHLAND = register("mushland");
//    public static final ResourceKey<Biome> PASTEL_PARCEL = register("pastel_parcel");
//    public static final ResourceKey<Biome> DWARVISH_LEFTOVERS = register("dwarvish_leftovers");

    protected static final int NORMAL_WATER_COLOR = 4159204;
    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;
    @Nullable
    private static final Music NORMAL_MUSIC = null;
    public static final int SWAMP_SKELETON_WEIGHT = 70;

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(TEST_BIOME, testBiome(context));
        context.register(MYSTIC_FOREST, mysticForest(context));
        context.register(STUBBY_WOODLAND, stubbyWoodland(context));
        context.register(SILVER_BIRCH_FOREST, silverBirchForest(context));
        context.register(DESOLATE_ICE_FIELDS, desolateIceFields(context));
        context.register(MIXED_WOODS, mixedWoods(context));
        context.register(ORDERLY_COURTS, orderlyCourts(context));
        context.register(ORDERLY_COURTS_RUINS, orderlyCourtsRuins(context));
        context.register(TREE_ON_TREE_FOREST, treeOnTreeForest(context));
        context.register(SHROOMLANDS, shroomlands(context));
        context.register(DUSTY_FLATS, dustyFlats(context));
        context.register(ANTHOCYANIN_FOREST, anthocyaninForest(context));
        context.register(FIELDS_OF_PAIN, fieldsOfPain(context));
        context.register(THRUMLETONS, thrumlethons(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome testBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // mystic
    public static Biome mysticForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MYSTIC_FLOWER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MYSTIC_AMETHYST_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MYSTIC_TREE_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(ParameterUtils.Temperature.COOL.ordinal())
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x8f3fe4)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0xa53fe4)
                        .grassColorOverride(0x8b5ac0)
                        .foliageColorOverride(0x8754ba)
                        .fogColor(0x943fe4)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // stubby
    public static Biome stubbyWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 1, 2, 6))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 10, 2, 3));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STUBBY_TREE_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(1.0F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0xccfc255)
                        .foliageColorOverride(0xAEA42A)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // silver birch
    public static Biome silverBirchForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        // we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GOLDEN_LEAF_LITTER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SILVER_BIRCH_FLOWER_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SILVER_BIRCH_TREE_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(ParameterUtils.Temperature.COOL.ordinal())
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0xFFD000)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .foliageColorOverride(0xFFFC33)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // desolate
    public static Biome desolateIceFields(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // no peaceful mobs
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        //
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacedFeatures.DESOLATE_SPIKE_PLACED_KEY);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        return new Biome.BiomeBuilder()
                // we do be make it custom tho
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x050505)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0x3E3942)
                        .grassColorOverride(0x5C5C5C)
                        .foliageColorOverride(0x4D4D4D)
                        .fogColor(0x121414)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(new Music(SoundEvents.MUSIC_BIOME_BASALT_DELTAS, 6000, 24000, true))
                        .build())
                .build();
    }

    // mixed
    public static Biome mixedWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MIXED_OAK_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x7fdb3d)
                        .foliageColorOverride(0x59AE30)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


    // orderly
    public static Biome orderlyCourts(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ORDERLY_FLOWER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TRIMMED_TREE_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0xA0D446)
                        .foliageColorOverride(0x6EDE0D)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // orderly
    public static Biome orderlyCourtsRuins(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ORDERLY_FLOWER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TRIMMED_TREE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacedFeatures.ORDERLY_COURTS_RUINS_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0xA0D446)
                        .foliageColorOverride(0x6EDE0D)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // tree on tree
    public static Biome treeOnTreeForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TREE_ON_TREE_TREE_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x66CC1F)
                        .foliageColorOverride(0x66CC1F)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


    // shroomlands
    public static Biome shroomlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.MOOSHROOM, 8, 4, 8));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.SHROOM_BOI.get(), 8, 2, 6));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        // we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addMushroomFieldVegetation(biomeBuilder);

        // tree
        // biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MIXED_OAK_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(1.0F)
                .temperature(0.9F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0xb791cc)
                        .foliageColorOverride(0xb791cc)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // dusty
    public static Biome dustyFlats(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

        // dead corals mayb?

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x6b6b6b)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .foliageColorOverride(0x6b6b6b)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // Anthocyanin forest
    public static Biome anthocyaninForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ANTHOCYANIN_FLOWER_PLACED_KEY);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ANTHOCYANIN_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x00AAFF)
                        .foliageColorOverride(0x00AAFF)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // pain
    public static Biome fieldsOfPain(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        // we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FIELDS_OF_PAIN_FILL_PLACED_KEY);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addSavannaGrass(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x58CC98)
//                        .foliageColorOverride(0x59AE30)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    // thrumlethons
    public static Biome thrumlethons(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // TODO: insert pump kin here
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        // we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.THICK_PUMPKIN_PLACED_KEY);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addSavannaGrass(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.6F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(7972607)
                        .grassColorOverride(0x32D17F)
//                        .foliageColorOverride(0x59AE30)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}
