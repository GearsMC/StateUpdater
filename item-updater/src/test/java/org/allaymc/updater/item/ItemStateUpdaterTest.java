package org.allaymc.updater.item;

import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author IWareQ
 */
class ItemStateUpdaterTest {
    @Test
    void remap_meta_for_coal() {
        var result = ItemStateUpdaters.updateItemState(
                NbtMap.builder()
                        .putString("Name", "minecraft:coal")
                        .putInt("Damage", 1)
                        .build(),
                ItemStateUpdaters.LATEST_VERSION
        );

        assertEquals("minecraft:charcoal", result.getString("Name"));
        assertEquals(0, result.getInt("Damage"));
    }

    @Test
    void unknown_remap_meta_for_coal() {
        var oldCoal = NbtMap.builder()
                .putString("Name", "minecraft:coal")
                .putInt("Damage", 2)
                .build();
        var newCoal = ItemStateUpdaters.updateItemState(oldCoal, ItemStateUpdaters.LATEST_VERSION);
        assertEquals(oldCoal, newCoal);
    }

    @Test
    void rename_id_for_record_creator() {
        var result = ItemStateUpdaters.updateItemState(
                NbtMap.builder()
                        .putString("Name", "minecraft:record_creator_music_box")
                        .build(),
                ItemStateUpdaters.LATEST_VERSION
        );

        assertEquals("minecraft:music_disc_creator_music_box", result.getString("Name"));
    }

    @Test
    void rename_id_for_grass() {
        var result = ItemStateUpdaters.updateItemState(
                NbtMap.builder()
                        .putString("Name", "minecraft:grass")
                        .build(),
                ItemStateUpdaters.LATEST_VERSION
        );

        assertEquals("minecraft:grass_block", result.getString("Name"));
    }

    @Test
    void remap_meta_for_stone() {
        assertDoesNotThrow(() -> {
            ItemStateUpdaters.updateItemState(
                    NbtMap.builder()
                            .putString("Name", "minecraft:stone")
                            .putInt("Damage", 0)
                            .build(),
                    ItemStateUpdaters.LATEST_VERSION
            );
        });
    }

    @Test
    void int_and_short_meta() {
        var resultInt = ItemStateUpdaters.updateItemState(
                NbtMap.builder()
                        .putString("Name", "minecraft:coal")
                        .putInt("Damage", 1)
                        .build(),
                ItemStateUpdaters.LATEST_VERSION
        );

        assertEquals("minecraft:charcoal", resultInt.getString("Name"));
        assertEquals(0, resultInt.getInt("Damage"));

        var resultShort = ItemStateUpdaters.updateItemState(
                NbtMap.builder()
                        .putString("Name", "minecraft:coal")
                        .putShort("Damage", (short) 1)
                        .build(),
                ItemStateUpdaters.LATEST_VERSION
        );

        assertEquals("minecraft:charcoal", resultShort.getString("Name"));
        assertEquals(0, resultShort.getShort("Damage"));
    }

    @Test
    void updates_from_1_26_40_to_1_26_60() {
        var bounce = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", "minecraft:record_bounce").build(),
                ItemStateUpdaters.LATEST_VERSION
        );
        assertEquals("minecraft:music_disc_bounce", bounce.getString("Name"));

        var poplarBoat = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", "minecraft:chest_boat").putShort("Damage", (short) 10).build(),
                ItemStateUpdaters.LATEST_VERSION
        );
        assertEquals("minecraft:poplar_chest_boat", poplarBoat.getString("Name"));

        var photo = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", "minecraft:photo").build(),
                ItemStateUpdaters.LATEST_VERSION
        );
        assertEquals("minecraft:photo_item", photo.getString("Name"));

        var frostbiteEgg = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", "minecraft:spawn_egg").putShort("Damage", (short) 155).build(),
                ItemStateUpdaters.LATEST_VERSION
        );
        assertEquals("minecraft:frostbite_spawn_egg", frostbiteEgg.getString("Name"));

        var before = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", "minecraft:spawn_egg").putShort("Damage", (short) 155).build(),
                ItemStateUpdater_1_26_50.INSTANCE.getVersion()
        );
        assertEquals("minecraft:spawn_egg", before.getString("Name"));
    }
}
