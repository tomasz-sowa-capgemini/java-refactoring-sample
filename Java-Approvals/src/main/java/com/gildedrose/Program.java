package com.gildedrose;

/**
 * Entry point for the Gilded Rose application.
 * Simulates item quality updates over a series of days.
 */
public class Program {

    private static final int SIMULATION_DAYS = 31;

    public static void main(String... args) {
        System.out.println("OMGHAI!");

        Item[] items = initializeItems();
        GildedRose gildedRoseApp = new GildedRose(items);

        simulateDays(gildedRoseApp, items, SIMULATION_DAYS);
    }

    /**
     * Initializes the list of items for the simulation.
     *
     * @return an array of {@link Item} objects
     */
    private static Item[] initializeItems() {
        return new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // This conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)
        };
    }

    /**
     * Simulates the passage of days, printing item states and updating their quality.
     *
     * @param gildedRoseApp the GildedRose application instance
     * @param items         the array of items to simulate
     * @param totalDays     the number of days to simulate
     */
    private static void simulateDays(GildedRose gildedRoseApp, Item[] items, int totalDays) {
        for (int day = 0; day < totalDays; day++) {
            printDayHeader(day);
            printItems(items);
            gildedRoseApp.updateQuality();
        }
    }

    /**
     * Prints the header for the current simulation day.
     *
     * @param day the current day number
     */
    private static void printDayHeader(int day) {
        System.out.println("-------- day " + day + " --------");
        System.out.println("name, sellIn, quality");
    }

    /**
     * Prints the details of all items.
     *
     * @param items the array of items to print
     */
    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }
}