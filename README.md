# StateUpdater

## GearsMC fork'u

Bu depo [AllayMC/StateUpdater](https://github.com/AllayMC/StateUpdater)'ın GearsMC çatalıdır.
[GearsMC/Allay](https://github.com/GearsMC/Allay) güncelleyicileri Maven'dan değil **kaynak olarak** kullanır
(`Allay/settings.gradle.kts` → `includeBuild("../StateUpdater")`), bu yüzden iki depo yan yana klonlanır.

- **Neden fork:** yukarı akışın 1.26.30 adımı Maven'a yayımlanmadı. 26.50'nin eklediği blok durumları
  (merdivende `minecraft:corner`; çit, cam panel, parmaklık ve tuzak ipinde `minecraft:connection_*`) için adımı
  biz yazıyoruz.
- **Sürüm:** fork'a özgü değişiklik içeren modülün sürümü `-G<n>` ekiyle yazılır (ör. `1.26.50-G1`), Allay'in
  `gradle/libs.versions.toml` dosyası da aynı sürümü ister. Bu sürüm Maven'da olmadığı için klasör eksikse Allay
  derlemesi sessizce eski kütüphaneye düşmez, açıkça durur.
- **Yukarı akışla eşitleme:** `git fetch upstream`, yeni commit'ler `git cherry-pick -x` ile alınır (özgün yazarlık
  korunur). Yukarı akışa PR açılmaz.
- **Yeni blok adımı:** `BlockStateUpdater_<sürüm>` sınıfı, `BlockStateUpdaters` listesine kayıt ve
  `BlockStateUpdaterTest`'e örnek giriş → beklenen çıkış testi. Güncelleyici kayıttaki sürüme bakmaz, her adımı hedef
  sürüme kadar eşleşen etikete uygular; bu yüzden adım zaten güncel olan veriye dokunmamalı (`addProperty` özellik
  varsa değeri korur). Ardından Allay'de `ProtocolInfo.BLOCK_STATE_UPDATER` yeni adıma çekilir.

---

![Block Updater](https://img.shields.io/maven-central/v/org.allaymc.stateupdater/block-updater?label=block-updater)
![Item Updater](https://img.shields.io/maven-central/v/org.allaymc.stateupdater/item-updater?label=item-updater)

Extracted state mappings from the Minecraft: Bedrock Edition used to update state NBT tags to the latest version.

All updaters are auto-generated!

### Usage

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // block-updater
    implementation("org.allaymc.stateupdater:block-updater:<version>")
    // item-updater
    implementation("org.allaymc.stateupdater:item-updater:<version>")
}
```

#### Update Item

```java
// Update legacy items to new latest items (example for update jungle planks)
NbtMap updatedNbt = ItemStateUpdaters.updateItemState(
        NbtMap.builder()
                .putString("Id", "5")
                .putInt("Damage", 3) // You can use int, short, long and byte
                .build(),
        ItemStateUpdaters.LATEST_VERSION
);

// or update new items to latest
NbtMap updatedNbt = ItemStateUpdaters.updateItemState(
        NbtMap.builder()
                .putString("Name", "minecraft:coal")
                .putInt("Damage", 1) // You can use int, short, long and byte
                .build(),
        ItemStateUpdaters.LATEST_VERSION
);
```

#### Update Block

```java
// Update legacy block
NbtMap updatedNbt = BlockStateUpdaters.updateBlockState(
        NbtMap.builder()
                .putString("name", "minecraft:stone")
                .putInt("val", 1) // You can use int, short, long and byte
                .build(),
        BlockStateUpdaters.LATEST_VERSION
);

// or update block
NbtMap updatedNbt = BlockStateUpdaters.updateBlockState(
        NbtMap.builder()
                .putString("name", "minecraft:anvil")
                .putCompound("states", NbtMap.builder()
                        .putString("damage", "very_damaged")
                        .putInt("direction", 3)
                        .build())
                .build(),
        BlockStateUpdaters.LATEST_VERSION
);
```

## Acknowledgements

* [BedrockItemUpgradeSchema](https://github.com/pmmp/BedrockItemUpgradeSchema)
* [BedrockBlockUpgradeSchema](https://github.com/pmmp/BedrockBlockUpgradeSchema)
* [BlockStateUpdater](https://github.com/CloudburstMC/BlockStateUpdater)
