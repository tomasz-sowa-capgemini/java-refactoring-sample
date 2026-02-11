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
     * Updates the quality and sellIn values of all items according to their specific rules.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    /**
     * Updates a single item's quality and sellIn values based on its type.
     *
     * @param item the item to update
     */
    private void updateItemQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                updateAgedBrie(item);
                break;
            case BACKSTAGE_PASS:
                updateBackstagePass(item);
                break;
            case SULFURAS:
                // Sulfuras does not change in quality or sellIn
                break;
            default:
                updateRegularItem(item);
                break;
        }

        if (!SULFURAS.equals(item.name)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item);
    }

    private void updateBackstagePass(Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) {
            increaseQuality(item);
        }

        if (item.sellIn < 6) {
            increaseQuality(item);
        }
    }

    private void updateRegularItem(Item item) {
        decreaseQuality(item);
    }

    private void handleExpiredItem(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                increaseQuality(item);
                break;
            case BACKSTAGE_PASS:
                item.quality = MIN_QUALITY;
                break;
            case SULFURAS:
                // Sulfuras does not change
                break;
            default:
                decreaseQuality(item);
                break;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }
}