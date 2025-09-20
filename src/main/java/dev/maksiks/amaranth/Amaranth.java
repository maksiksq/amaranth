package dev.maksiks.amaranth;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.event.ModFogHandler;
import dev.maksiks.amaranth.item.ModCreativeModeTabs;
import dev.maksiks.amaranth.item.ModItems;
import dev.maksiks.amaranth.particle.ModParticles;
import dev.maksiks.amaranth.worldgen.biome.ModTerrablender;
import dev.maksiks.amaranth.worldgen.biome.surface.ModSurfaceRules;
import dev.maksiks.amaranth.worldgen.features.ModFeatures;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.ModFoliagePlacerTypes;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Amaranth.MOD_ID)
public class Amaranth {
    public static final String MOD_ID = "amaranth";
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Amaranth(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (amaranth) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModParticles.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        ModTerrablender.registerBiomes();

        // registering trunk and foliage placer types
        ModTrunkPlacerTypes.TRUNK_PLACER_TYPES.register(modEventBus);
        ModFoliagePlacerTypes.FOLIAGE_PLACER_TYPES.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.MAFIA_BLOB);
            event.accept(ModItems.BEANIE_BLOB);
        }

        // not putting much stuff into vanilla tabs because you can just find it in the mod tab
        // and thus less thinking for me

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.HEXFRUIT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
