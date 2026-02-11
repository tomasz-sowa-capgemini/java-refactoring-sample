package com.gildedrose;

/**
 * Represents an item with a name, sell-in value, and quality.
 * This class is immutable and provides a string representation of the item.
 */
public class Item {

    private final String name;
    private final int sellIn;
    private final int quality;

    /**
     * Constructs an {@code Item} instance.
     *
     * @param name    the name of the item
     * @param sellIn  the number of days to sell the item
     * @param quality the quality of the item
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    /**
     * Returns the name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of days remaining to sell the item.
     *
     * @return the sell-in value
     */
    public int getSellIn() {
        return sellIn;
    }

    /**
     * Returns the quality of the item.
     *
     * @return the item quality
     */
    public int getQuality() {
        return quality;
    }

    /**
     * Returns a string representation of the item in the format:
     * "name, sellIn, quality".
     *
     * @return a string describing the item
     */
    @Override
    public String toString() {
        return String.format("%s, %d, %d", name, sellIn, quality);
    }
}