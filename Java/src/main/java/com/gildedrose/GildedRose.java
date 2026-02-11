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
     * Updates the quality and sell-in values for all items based on their type.
     * The rules for updating are defined per item category.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (isSulfuras(item)) {
            return; // Legendary item, does not change
        }

        updateQualityBeforeSellDate(item);
        decrementSellIn(item);
        updateQualityAfterSellDate(item);
    }

    private void updateQualityBeforeSellDate(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }
            if (item.sellIn < 6) {
                increaseQuality(item);
            }
        } else {
            decreaseQuality(item);
        }
    }

    private void updateQualityAfterSellDate(Item item) {
        if (item.sellIn >= 0) {
            return;
        }

        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            item.quality = MIN_QUALITY;
        } else {
            decreaseQuality(item);
        }
    }

    private void decrementSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality -= 1;
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