package dev.maksiks.amaranth.worldgen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.common.world.NoneBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class ModBiomeModifiers {
    // note: i could not get it to spawn as a CREATURE
    // (some weird spawn condition im not hitting?)
    // so doing this instead, kind of wonk but the best i could get
    // was a MONSTER which is kind of weird
    public static final ResourceKey<BiomeModifier> ADD_SHROOM_BOI_SPAWNS = registerKey("add_shroom_boi_spawns");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // why 400? so high? and it doesn't spawn as creature in the biome
        // im confused, already spent a few hours figuring out why it doesn't spawn normally,
        // something is definitely wrong here
//        context.register(ADD_SHROOM_BOI_SPAWNS, new BiomeModifiers.AddSpawnsBiomeModifier(
//                HolderSet.direct(biomes.getOrThrow(ModBiomes.SHROOMLANDS), biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(ModBiomes.MIXED_WOODS)),
//                List.of(
//                        new MobSpawnSettings.SpawnerData(ModEntities.SHROOM_BOI.get(), 400, 1 ,4)
//                )
//        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }
}
