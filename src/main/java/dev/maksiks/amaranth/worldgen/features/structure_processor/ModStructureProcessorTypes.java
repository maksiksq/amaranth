package dev.maksiks.amaranth.worldgen.features.structure_processor;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR;

public class ModStructureProcessorTypes {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES =
            DeferredRegister.create(STRUCTURE_PROCESSOR, Amaranth.MOD_ID);

    public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<GiganticSatistreeStructureProcessor>>
            GIGANTIC_SATISTREE_PROCESSOR =
            STRUCTURE_PROCESSOR_TYPES.register(
                    "gigantic_satistree_processor",
                    () -> () -> GiganticSatistreeStructureProcessor.CODEC
            );
}