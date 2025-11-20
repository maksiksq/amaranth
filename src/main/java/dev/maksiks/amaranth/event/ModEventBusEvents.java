package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.entity.client.ShroomBoiModel;
import dev.maksiks.amaranth.entity.custom.ShroomBoiEntity;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static dev.maksiks.amaranth.block.ModBlocks.MOD_FLOWER_POTS;

@EventBusSubscriber(modid = Amaranth.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ShroomBoiModel.LAYER_LOCATION, ShroomBoiModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SHROOM_BOI.get(), ShroomBoiEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntities.SHROOM_BOI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, reason, pos, random) -> {
                    return true;
                },
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MOD_FLOWER_POTS.forEach((plant, pot) -> {
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(
                        ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, plant.getId().getPath()),
                        pot
                );
            });
        });
    }

    @SubscribeEvent
    public static void onCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (event.getCrafting().is(ModBlocks.WISTERIA_LOG.asItem())) {
            ItemStack secondary = new ItemStack(ModItems.WISTERIA_JUICE.asItem());
            if (!event.getEntity().getInventory().add(secondary)) {
                event.getEntity().drop(secondary, false);
            }
        }
    }
}
