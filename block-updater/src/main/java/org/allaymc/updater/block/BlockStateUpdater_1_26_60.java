package org.allaymc.updater.block;

import org.allaymc.updater.block.context.BlockUpdaterContext;

/**
 * 1.26.60: kızıl taş tozuna {@code redstone_north/east/south/west}, koro bitkisine
 * {@code minecraft:connection_north/east/south/west/up/down}, ateşe {@code minecraft:connection_north/east/south/west/up}
 * eklendi.
 *
 * <p>GearsMC fork: Mojang blok sürüm numarasını yine artırmadı (paletlerde hâlâ 1.21.60.33). Durumlar 1.26.50 ve
 * 1.26.60 resmî paletlerinin (CloudburstMC/Data {@code 7046791} → {@code 293c848}) farkından çıkarıldı ve PocketMine'ın
 * {@code 0361_1.26.50_to_1.26.60.json} şemasıyla birebir aynı: başka tür değişmedi, yeni türler ({@code icicle},
 * {@code ice_crystal}, {@code disc_press}) eski dünyalarda bulunmaz. Eklenen değerler varsayılandır: kızıl taş yönü
 * {@code none}, bağlantı {@code 0}. Gerçek değerler komşulara bağlıdır, onları sunucu hesaplar. Bağlantı değeri byte
 * olmalı: NBT tipi blok durumu hash'ine girer. Ateşin aşağı bağlantısı yok; alttaki blok ateşi zaten taşır.</p>
 */
public class BlockStateUpdater_1_26_60 extends BlockStateUpdater {
    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_26_60();

    static final String[] REDSTONE_DIRECTIONS = {"north", "east", "south", "west"};
    static final String[] CHORUS_PLANT_CONNECTIONS = {"north", "east", "south", "west", "up", "down"};
    static final String[] FIRE_CONNECTIONS = {"north", "east", "south", "west", "up"};

    private BlockStateUpdater_1_26_60() {
        super(1, 26, 60);
    }

    @Override
    public void registerUpdaters(BlockUpdaterContext context) {
        for (var direction : REDSTONE_DIRECTIONS) {
            context.addProperty("minecraft:redstone_wire", "redstone_" + direction, "none");
        }
        for (var direction : CHORUS_PLANT_CONNECTIONS) {
            context.addProperty("minecraft:chorus_plant", "minecraft:connection_" + direction, (byte) 0);
        }
        for (var direction : FIRE_CONNECTIONS) {
            context.addProperty("minecraft:fire", "minecraft:connection_" + direction, (byte) 0);
        }
    }
}
