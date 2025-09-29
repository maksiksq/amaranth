package dev.maksiks.amaranth.sound;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.JukeboxBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Amaranth.MOD_ID);

    public static final Supplier<SoundEvent> ARCTIC_WIND_THRONGLED = registerSoundEvent("arctic_wind_throngled");

    public static Holder.Reference<SoundEvent> getSoundHolder(Supplier<SoundEvent> supplier) {
        return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
                BuiltInRegistries.SOUND_EVENT.getResourceKey(supplier.get())
                        .orElseThrow()
        );
    }

    public static final Supplier<SoundEvent> PALETTE_OVERLOAD = registerSoundEvent("palette_overload");
    public static final ResourceKey<JukeboxSong> PALETTE_OVERLOAD_KEY = createSong("palette_overload");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
