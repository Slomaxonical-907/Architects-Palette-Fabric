package com.slomaxonical.architectspalette.core.registry;

import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;


public class APSounds {

    public static final SoundEvent BLOCK_ENTWINE_PLACE = createSounds("block.entwine.place");
    public static final SoundEvent BLOCK_ENTWINE_HIT = createSounds("block.entwine.hit");

    public static final SoundEvent ITEM_WARPS = createSounds("block.warping.item_warps");
    public static final SoundEvent CAGE_LANTERN_TOGGLE_ON = createSounds("block.cage_lantern.toggle_on");
    public static final SoundEvent CAGE_LANTERN_TOGGLE_OFF = createSounds("block.cage_lantern.toggle_off");

    private static SoundEvent createSounds(String key) {
        Identifier id = new Identifier(ArchitectsPalette.MOD_ID, key);
        return Registry.register(Registry.SOUND_EVENT,id, new SoundEvent(id));
    }
    public static void registerSounds(){}

    public static class APSoundTypes {
        public static final BlockSoundGroup ENTWINE = new LazySoundType(
                1.0F, 1.0F,
                new Lazy<>(() -> SoundEvents.BLOCK_STONE_BREAK),
                new Lazy<>(() -> SoundEvents.BLOCK_GLASS_STEP),
                new Lazy<>(() -> BLOCK_ENTWINE_PLACE),
                new Lazy<>(() -> BLOCK_ENTWINE_HIT),
                new Lazy<>(() -> SoundEvents.BLOCK_GLASS_FALL)
//                new Lazy<>(BLOCK_ENTWINE_PLACE),
//                new Lazy<>(BLOCK_ENTWINE_HIT),
        );

        public static final BlockSoundGroup ENDER_PEARL = new BlockSoundGroup(
                1.0F, 2.0F,
                SoundEvents.BLOCK_SHROOMLIGHT_BREAK,
                SoundEvents.BLOCK_SHROOMLIGHT_STEP,
                SoundEvents.BLOCK_SHROOMLIGHT_PLACE,
                SoundEvents.BLOCK_SHROOMLIGHT_HIT,
                SoundEvents.BLOCK_SHROOMLIGHT_FALL
        );
    }

    private static class LazySoundType extends BlockSoundGroup {
        private final Lazy<SoundEvent> breakSound;
        private final Lazy<SoundEvent> stepSound;
        private final Lazy<SoundEvent> placeSound;
        private final Lazy<SoundEvent> hitSound;
        private final Lazy<SoundEvent> fallSound;

        public LazySoundType(float volumeIn, float pitchIn, Lazy<SoundEvent> breakSoundIn, Lazy<SoundEvent> stepSoundIn, Lazy<SoundEvent> placeSoundIn, Lazy<SoundEvent> hitSoundIn, Lazy<SoundEvent> fallSoundIn) {
            super(volumeIn, pitchIn, SoundEvents.BLOCK_STONE_BREAK, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL);
            // now we're talking
            this.breakSound = breakSoundIn;
            this.stepSound = stepSoundIn;
            this.placeSound = placeSoundIn;
            this.hitSound = hitSoundIn;
            this.fallSound = fallSoundIn;
        }

        public float getVolume() {
            return this.volume;
        }

        public float getPitch() {
            return this.pitch;
        }

        public SoundEvent getBreakSound() {
            return this.breakSound.get();
        }

        public SoundEvent getStepSound() {
            return this.stepSound.get();
        }

        public SoundEvent getPlaceSound() {
            return this.placeSound.get();
        }

        public SoundEvent getHitSound() {
            return this.hitSound.get();
        }

        public SoundEvent getFallSound() {
            return this.fallSound.get();
        }

    }

}
