package dev.maksiks.amaranth.particle;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Amaranth.MOD_ID);

    public static final Supplier<SimpleParticleType> SILVER_BIRCH_PARTICLES =
            PARTICLE_TYPES.register("silver_birch_particles",
            () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> ANTHOCYANIN_PARTICLES =
            PARTICLE_TYPES.register("anthocyanin_particles",
            () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
