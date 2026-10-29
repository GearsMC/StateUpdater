package org.allaymc.updater.item;

import org.allaymc.updater.item.context.ItemUpdaterContext;
import org.allaymc.updater.item.context.RemapMetaEntry;

/**
 * GearsMC fork: yukarı akışın {@code c618e5b} commit'indeki adımla aynı, kaynak PocketMine
 * {@code 0271_1.26.20_to_1.26.40.31_beta.json}.
 *
 * @author IWareQ
 */
public class ItemStateUpdater_1_26_40 extends ItemStateUpdater {
    public static final ItemStateUpdater INSTANCE = new ItemStateUpdater_1_26_40();

    private ItemStateUpdater_1_26_40() {
        super(1, 26, 40);
    }

    @Override
    public void registerUpdaters(ItemUpdaterContext context) {
        context.renameId("minecraft:record_bounce", "minecraft:music_disc_bounce");

        context.remapMeta("minecraft:chest_boat",
                new RemapMetaEntry(10, "minecraft:poplar_chest_boat")
        );
    }
}
