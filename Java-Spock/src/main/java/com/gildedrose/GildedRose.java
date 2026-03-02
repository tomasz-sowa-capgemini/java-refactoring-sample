package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (isSulfuras(item)) {
            return;
        }

        updateQualityBeforeSellIn(item);
        item.sellIn -= 1;

        if (item.sellIn < 0) {
            updateQualityAfterSellIn(item);
        }
    }

    private void updateQualityBeforeSellIn(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
            return;
        }

        if (isBackstagePass(item)) {
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }
            if (item.sellIn < 6) {
                increaseQuality(item);
            }
            return;
        }

        decreaseQuality(item);
    }

    private void updateQualityAfterSellIn(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
            return;
        }

        if (isBackstagePass(item)) {
            item.quality = 0;
            return;
        }

        decreaseQuality(item);
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.name);
    }

    private boolean isBackstagePass(Item item) {
        return BACKSTAGE_PASSES.equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS.equals(item.name);
    }
}
