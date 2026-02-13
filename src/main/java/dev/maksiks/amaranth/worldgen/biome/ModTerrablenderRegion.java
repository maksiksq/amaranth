package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.Config;
import dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectors;
import dev.maksiks.amaranth.worldgen.biome.selector.TerrablenderBiomeSelectors;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import org.apache.commons.lang3.mutable.MutableInt;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static dev.maksiks.amaranth.worldgen.biome.ModRegionUtils.*;

//
// Terrablender has no docs and this is unexplained as hell so took reference from
// BWG, thanks!
//

public class ModTerrablenderRegion extends Region {
    public static ModTerrablenderRegion REGION_0;
    public static ModTerrablenderRegion REGION_1;
    public static ModTerrablenderRegion REGION_2;
    public static ModTerrablenderRegion REGION_3;

    public static void init() {
        REGION_0 = new ModTerrablenderRegion(
                0,
                9,
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_OCEANS),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES_VARIANT),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PLATEAU_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PLATEAU_BIOMES_VARIANT),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_SHATTERED_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_BEACH_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PEAK_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PEAK_BIOMES_VARIANT),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_SLOPE_BIOMES),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER)
        );

        REGION_1 = new ModTerrablenderRegion(
                1,
                9,
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_OCEANS),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES_2),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES_VARIANT_2),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PLATEAU_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PLATEAU_BIOMES_VARIANT_2),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_SHATTERED_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_BEACH_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PEAK_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PEAK_BIOMES_VARIANT),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_SLOPE_BIOMES),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_SLOPE_BIOMES_VARIANT)
        );

        REGION_2 = new ModTerrablenderRegion(
                2,
                2,
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_OCEANS),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES_3),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.MIDDLE_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PLATEAU_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PLATEAU_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SHATTERED_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.BEACH_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER)
        );

        REGION_3 = new ModTerrablenderRegion(
                3,
                2,
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.OCEANS_TERRABLENDER),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_MIDDLE_BIOMES_4),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.MIDDLE_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MOD_PLATEAU_BIOMES_4),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PLATEAU_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SHATTERED_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.BEACH_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER)
        );

        Regions.register(REGION_0);
        Regions.register(REGION_1);
        Regions.register(REGION_2);
        Regions.register(REGION_3);
    }

    private static int count = 0;
    private final RandomSource random = RandomSource.create();

    private final Set<ResourceKey<Biome>> modKeys = new ObjectOpenHashSet<>();
    private final ModTerrablenderOverworldBiomeBuilder terrablenderOverworldBiomeBuilder;

    public ModTerrablenderRegion(int regionId, int overworldWeight,
                                 ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middleBiomes,
                                 ResourceKey<Biome>[][] middleBiomesVariant, ResourceKey<Biome>[][] plateauBiomes,
                                 ResourceKey<Biome>[][] plateauBiomesVariant, ResourceKey<Biome>[][] shatteredBiomes,
                                 ResourceKey<Biome>[][] beachBiomes, ResourceKey<Biome>[][] peakBiomes,
                                 ResourceKey<Biome>[][] peakBiomesVariant, ResourceKey<Biome>[][] slopeBiomes, ResourceKey<Biome>[][] slopeBiomesVariant
    ) {
        super(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "region_" + count++), RegionType.OVERWORLD, overworldWeight);

        Predicate<ResourceKey<Biome>> noVoidBiomes = biomeResourceKey -> biomeResourceKey != Biomes.THE_VOID;
        oceans = sanitize("oceans", this.getName(), count, oceans, noVoidBiomes, true);
        middleBiomes = sanitize("middle_biomes", this.getName(), count, middleBiomes, noVoidBiomes, true);
        plateauBiomes = sanitize("plateau_biomes", this.getName(), count, plateauBiomes, noVoidBiomes, true);
        beachBiomes = sanitize("beach_biomes", this.getName(), count, beachBiomes, noVoidBiomes, true);
        slopeBiomes = sanitize("slope_biomes", this.getName(), count, slopeBiomes, noVoidBiomes, true);

        middleBiomesVariant = sanitize("middle_biomes_variant", this.getName(), count, middleBiomesVariant, noVoidBiomes, false);
        plateauBiomesVariant = sanitize("plateau_biomes_variant", this.getName(), count, plateauBiomesVariant, noVoidBiomes, false);
        shatteredBiomes = sanitize("shattered_biomes", this.getName(), count, shatteredBiomes, noVoidBiomes, false);
        peakBiomes = sanitize("peak_biomes", this.getName(), count, peakBiomes, noVoidBiomes, false);
        peakBiomesVariant = sanitize("peak_biomes_variant", this.getName(), count, peakBiomesVariant, noVoidBiomes, false);
        slopeBiomesVariant = sanitize("slope_biomes_variant", this.getName(), count, slopeBiomesVariant, noVoidBiomes, false);

        this.terrablenderOverworldBiomeBuilder = new ModTerrablenderOverworldBiomeBuilder(
                oceans, middleBiomes, middleBiomesVariant,
                plateauBiomes, plateauBiomesVariant, shatteredBiomes,
                beachBiomes, peakBiomes, peakBiomesVariant, slopeBiomes, slopeBiomesVariant, regionId
        );

        forEachBiome((biomeResourceKey -> {
            if (biomeResourceKey != null) {
                modKeys.add(biomeResourceKey);
            }
        }), oceans, middleBiomes, middleBiomesVariant, plateauBiomes, plateauBiomesVariant, shatteredBiomes, beachBiomes, peakBiomes);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        MutableInt totalPairs = new MutableInt();
        MutableInt modMapperAccepted = new MutableInt(0);

        this.terrablenderOverworldBiomeBuilder.addBiomesPublic((parameterPointResourceKeyPair -> {
            Climate.ParameterPoint parameterPoint = parameterPointResourceKeyPair.getFirst();
            ResourceKey<Biome> biomeKey = parameterPointResourceKeyPair.getSecond();

            if (biomeKey == null) {
                mapper.accept(new Pair<>(parameterPoint, Region.DEFERRED_PLACEHOLDER));
                modMapperAccepted.increment();
                totalPairs.increment();
                return;
            }

            if (!registry.containsKey(biomeKey)) {
                throw new IllegalArgumentException(String.format("\"%s\" is not a valid biome in the world registry!", biomeKey.location()));
            }

            if (!modKeys.contains(biomeKey) || Config.isBiomeEnabled(biomeKey)) {
                mapper.accept(new Pair<>(parameterPoint, biomeKey));
                modMapperAccepted.increment();
            } else {
                mapper.accept(new Pair<>(parameterPoint, Region.DEFERRED_PLACEHOLDER));
                modMapperAccepted.increment();
            }
            totalPairs.increment();
        }));

        int totalPairsValue = totalPairs.intValue();
        int mapperAcceptValue = modMapperAccepted.intValue();
        boolean sanityCheck = totalPairsValue != mapperAcceptValue;
        if (sanityCheck) {
            throw new UnsupportedOperationException(String.format("Amaranth: Not all biome parameter points were accepted for a biome region: %s. %s/%s were accepted.", this.getName().toString(), totalPairsValue, mapperAcceptValue));
        }
    }
}