package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Updates the quality and sell-in values for all items in the inventory.
     * The rules vary depending on the item type.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (!isAgedBrie(item) && !isBackstagePass(item)) {
            degradeQuality(item);
        } else {
            improveQuality(item);
        }

        if (!isSulfuras(item)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    private void degradeQuality(Item item) {
        if (item.quality > MIN_QUALITY && !isSulfuras(item)) {
            item.quality--;
        }
    }

    private void improveQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
            if (isBackstagePass(item)) {
                increaseBackstagePassQuality(item);
            }
        }
    }

    private void increaseBackstagePassQuality(Item item) {
        if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
            item.quality++;
        }
        if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void handleExpiredItem(Item item) {
        if (isAgedBrie(item)) {
            if (item.quality < MAX_QUALITY) {
                item.quality++;
            }
        } else if (isBackstagePass(item)) {
            item.quality = MIN_QUALITY;
        } else {
            degradeQuality(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.name);
    }

    private boolean isBackstagePass(Item item) {
        return BACKSTAGE_PASS.equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS.equals(item.name);
    }
}