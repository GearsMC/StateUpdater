package org.allaymc.updater.item;

import org.allaymc.updater.item.context.ItemUpdaterContext;
import org.allaymc.updater.item.context.RemapMetaEntry;

/**
 * 1.26.60: eski {@code minecraft:spawn_egg} 155 verisi {@code minecraft:frostbite_spawn_egg} oldu.
 *
 * <p>GearsMC fork: kaynak PocketMine {@code 0291_1.26.50_to_1.26.60.json}; CloudburstMC/Data'nın
 * {@code item_mappings.json} dosyası da aynı eşlemeyi ekledi.</p>
 */
public class ItemStateUpdater_1_26_60 extends ItemStateUpdater {
    public static final ItemStateUpdater INSTANCE = new ItemStateUpdater_1_26_60();

    private ItemStateUpdater_1_26_60() {
        super(1, 26, 60);
    }

    @Override
    public void registerUpdaters(ItemUpdaterContext context) {
        context.remapMeta("minecraft:spawn_egg",
                new RemapMetaEntry(155, "minecraft:frostbite_spawn_egg")
        );
    }
}
