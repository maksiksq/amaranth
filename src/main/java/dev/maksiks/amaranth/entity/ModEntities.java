package dev.maksiks.amaranth.entity;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.entity.custom.ShroomBoiEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Amaranth.MOD_ID);

    public static final Supplier<EntityType<ShroomBoiEntity>> SHROOM_BOI =
            ENTITY_TYPES.register("shroom_boi", () -> EntityType.Builder.of(ShroomBoiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.0f).build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "shroom_boi"))));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
