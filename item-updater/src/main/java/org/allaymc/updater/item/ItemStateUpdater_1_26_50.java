package org.allaymc.updater.item;

import org.allaymc.updater.item.context.ItemUpdaterContext;

/**
 * GearsMC fork: yukarı akışın {@code c618e5b} commit'indeki adımla aynı, kaynak PocketMine
 * {@code 0281_1.26.40_to_1.26.50.json}.
 *
 * @author IWareQ
 */
public class ItemStateUpdater_1_26_50 extends ItemStateUpdater {
    public static final ItemStateUpdater INSTANCE = new ItemStateUpdater_1_26_50();

    private ItemStateUpdater_1_26_50() {
        super(1, 26, 50);
    }

    @Override
    public void registerUpdaters(ItemUpdaterContext context) {
        context.renameId("minecraft:photo", "minecraft:photo_item");
    }
}
