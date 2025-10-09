package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectors;
import dev.maksiks.amaranth.worldgen.biome.selector.TerrablenderBiomeSelectors;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
// Terrablender has no docs and this is unexplained as hell so took some reference from
// BWG, thanks!
//

public class ModTerrablenderRegions extends Region {
    public static ModTerrablenderRegions REGION_1;
    public static ModTerrablenderRegions REGION_2;
    public static ModTerrablenderRegions REGION_3;
    public static ModTerrablenderRegions REGION_4;

    public static void init() {
        REGION_1 = new ModTerrablenderRegions(
                8,
                ModRegionUtils.to2DArray(ModBiomeSelectors.OCEANS_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.SHATTERED_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.BEACH_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PEAK_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PEAK_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.SLOPE_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER),
                new IdentityHashMap<>(),
                Map.of()
        );

        REGION_2 = new ModTerrablenderRegions(
                8,
                ModRegionUtils.to2DArray(ModBiomeSelectors.OCEANS_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.SHATTERED_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.BEACH_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PEAK_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PEAK_BIOMES_VARIANT_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.SLOPE_BIOMES_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER),
                new IdentityHashMap<>(),
                Map.of()
        );

        REGION_3 = new ModTerrablenderRegions(
                4,
                ModRegionUtils.to2DArray(ModBiomeSelectors.OCEANS_2_AMARANTH),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_2_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.MIDDLE_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_2_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PLATEAU_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SHATTERED_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.BEACH_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER),
                new IdentityHashMap<>(),
                Map.of()
        );

        REGION_4 = new ModTerrablenderRegions(
                2,
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.OCEANS_TERRABLENDER),
                ModRegionUtils.to2DArray(ModBiomeSelectors.MIDDLE_BIOMES_3_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.MIDDLE_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(ModBiomeSelectors.PLATEAU_BIOMES_3_AMARANTH),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PLATEAU_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SHATTERED_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.BEACH_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.PEAK_BIOMES_VARIANT_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_TERRABLENDER),
                ModRegionUtils.to2DArray(TerrablenderBiomeSelectors.SLOPE_BIOMES_VARIANT_TERRABLENDER),
                new IdentityHashMap<>(),
                Map.of()
        );

        Regions.register(REGION_1);
        Regions.register(REGION_2);
        Regions.register(REGION_3);
        Regions.register(REGION_4);
    }

    private static int count = 0;

    private final Set<ResourceKey<Biome>> bwgKeys = new ObjectOpenHashSet<>();
    private final Map<ResourceKey<Biome>, ResourceKey<Biome>> swapper;
    private final Map<ResourceKey<Biome>, ResourceKey<Biome>> globalSwapper;
    private final ModTerrablenderOverworldBiomeBuilder terrablenderOverworldBiomeBuilder;

    public ModTerrablenderRegions(int overworldWeight,
                                  ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middleBiomes,
                                  ResourceKey<Biome>[][] middleBiomesVariant, ResourceKey<Biome>[][] plateauBiomes,
                                  ResourceKey<Biome>[][] plateauBiomesVariant, ResourceKey<Biome>[][] shatteredBiomes,
                                  ResourceKey<Biome>[][] beachBiomes, ResourceKey<Biome>[][] peakBiomes,
                                  ResourceKey<Biome>[][] peakBiomesVariant, ResourceKey<Biome>[][] slopeBiomes, ResourceKey<Biome>[][] slopeBiomesVariant,
                                  Map<ResourceKey<Biome>, ResourceKey<Biome>> swapper, Map<ResourceKey<Biome>, ResourceKey<Biome>> globalSwapper) {
        super(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "region_" + count++), RegionType.OVERWORLD, overworldWeight);
        this.swapper = swapper;
        this.globalSwapper = globalSwapper;

        Predicate<ResourceKey<Biome>> noVoidBiomes = biomeResourceKey -> biomeResourceKey != Biomes.THE_VOID;
        oceans = sanitize("oceans", this.getName(), count, oceans, noVoidBiomes, true);
        middleBiomes = sanitize("middle_biomes", this.getName(), count, middleBiomes, noVoidBiomes, true);
        plateauBiomes = sanitize("plateau_biomes", this.getName(), count, plateauBiomes, noVoidBiomes, true);
        beachBiomes = sanitize("beach_biomes", this.getName(), count, beachBiomes, noVoidBiomes, true);
        slopeBiomes = sanitize("slope_biomes", this.getName(), count, slopeBiomes, noVoidBiomes, true);

        // these can contain the void somehow? not sure how this works
        middleBiomesVariant = sanitize("middle_biomes_variant", this.getName(), count, middleBiomesVariant, noVoidBiomes, false);
        plateauBiomesVariant = sanitize("plateau_biomes_variant", this.getName(), count, plateauBiomesVariant, noVoidBiomes, false);
        shatteredBiomes = sanitize("shattered_biomes", this.getName(), count, shatteredBiomes, noVoidBiomes, false);
        peakBiomes = sanitize("peak_biomes", this.getName(), count, peakBiomes, noVoidBiomes, false);
        peakBiomesVariant = sanitize("peak_biomes_variant", this.getName(), count, peakBiomesVariant, noVoidBiomes, false);
        slopeBiomesVariant = sanitize("slope_biomes_variant", this.getName(), count, slopeBiomesVariant, noVoidBiomes, false);

        this.terrablenderOverworldBiomeBuilder = new ModTerrablenderOverworldBiomeBuilder(
                oceans, middleBiomes, middleBiomesVariant,
                plateauBiomes, plateauBiomesVariant, shatteredBiomes,
                beachBiomes, peakBiomes, peakBiomesVariant, slopeBiomes, slopeBiomesVariant
        );

        forEachBiome((biomeResourceKey -> {
            if (biomeResourceKey != null) {
                bwgKeys.add(biomeResourceKey);
                if (swapper.containsValue(biomeResourceKey)) {
                    throw new IllegalArgumentException("Swapper cannot contain elements found in the temperature arrays.");
                }
            }
        }), oceans, middleBiomes, middleBiomesVariant, plateauBiomes, plateauBiomesVariant, shatteredBiomes, beachBiomes, peakBiomes);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        MutableInt totalPairs = new MutableInt();
        MutableInt amaranthMapperAccepted = new MutableInt(0);
        this.terrablenderOverworldBiomeBuilder.addBiomesPublic((parameterPointResourceKeyPair -> {
            Climate.ParameterPoint parameterPoint = parameterPointResourceKeyPair.getFirst();
            ResourceKey<Biome> biomeKey = parameterPointResourceKeyPair.getSecond();

            if (biomeKey == null) {
                mapper.accept(new Pair<>(parameterPoint, Region.DEFERRED_PLACEHOLDER));
                amaranthMapperAccepted.increment();
                totalPairs.increment();
                return;
            }

            if (!registry.containsKey(biomeKey)) {
                throw new IllegalArgumentException(String.format("\"%s\" is not a valid biome in the world registry!", biomeKey.location()));
            }
            // TODO: ADD CONFIG LATER
            if (true) {
                totalPairs.increment();
                boolean mapped = false;
                boolean alreadyMappedOutsideSwapper = false;
                if (this.bwgKeys.contains(biomeKey)) {
                    mapper.accept(new Pair<>(parameterPoint, this.globalSwapper.getOrDefault(biomeKey, biomeKey)));
                    amaranthMapperAccepted.increment();
                    alreadyMappedOutsideSwapper = true;
                    mapped = true;
                }

//                if (this.swapper.containsKey(biomeKey)) {
//                    if (alreadyMappedOutsideSwapper) {
//                        throw new UnsupportedOperationException(String.format("Attempting to assign a biome resource key in both the swapper and biome selectors. We're crashing your game to let you know that \"%s\" was put in the biome selectors but will always be swapped by \"%s\" due to the swapper. In region \"%s\".", biomeKey.location(), this.swapper.get(biomeKey).location(), this.getName().toString()));
//                    }
//                    ResourceKey<Biome> replacement = this.swapper.get(biomeKey);
//                    ResourceKey<Biome> biomeResourceKey = BWGWorldGenConfig.INSTANCE.get().enabledBiomes().getOrDefault(replacement, true) ? replacement : Region.DEFERRED_PLACEHOLDER;
//                    mapper.accept(new Pair<>(parameterPoint, this.globalSwapper.getOrDefault(biomeResourceKey, biomeResourceKey)));
//                    bygMapperAccepted.increment();
//                    mapped = true;
//                }

                if (!mapped) {
                    mapper.accept(new Pair<>(parameterPoint, this.globalSwapper.getOrDefault(biomeKey, biomeKey)));
                    amaranthMapperAccepted.increment();
                }
            } else {
                mapper.accept(new Pair<>(parameterPoint, Region.DEFERRED_PLACEHOLDER));
            }
        }));
        int totalPairsValue = totalPairs.intValue();
        int mapperAcceptValue = amaranthMapperAccepted.intValue();
        boolean sanityCheck = totalPairsValue != mapperAcceptValue;
        if (sanityCheck) {
            throw new UnsupportedOperationException(String.format("Not all biome parameter points were accepted for BWG Terrablender biome region: %s. %s/%s were accepted.", this.getName().toString(), totalPairsValue, mapperAcceptValue));
        }
    }
}