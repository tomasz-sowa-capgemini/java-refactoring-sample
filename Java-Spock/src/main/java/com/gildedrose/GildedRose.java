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
     * Updates the quality and sell-in values of all items according to their specific rules.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    /**
     * Updates the quality and sell-in of a single item based on its type.
     *
     * @param item the item to update
     */
    private void updateItemQuality(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASS)) {
            degradeQuality(item);
        } else {
            improveQuality(item);
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    /**
     * Decreases the quality of a normal item.
     */
    private void degradeQuality(Item item) {
        if (item.quality > MIN_QUALITY && !item.name.equals(SULFURAS)) {
            item.quality--;
        }
    }

    /**
     * Increases the quality of special items like Aged Brie and Backstage passes.
     */
    private void improveQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
            if (item.name.equals(BACKSTAGE_PASS)) {
                if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
                    item.quality++;
                }
                if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
                    item.quality++;
                }
            }
        }
    }

    /**
     * Handles quality updates for items that have passed their sell-by date.
     */
    private void handleExpiredItem(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            if (item.quality < MAX_QUALITY) {
                item.quality++;
            }
        } else if (item.name.equals(BACKSTAGE_PASS)) {
            item.quality = MIN_QUALITY;
        } else {
            degradeQuality(item);
        }
    }
}