package com.gildedrose;

/**
 * The {@code TexttestFixture} class serves as a simple test harness for the GildedRose application.
 * It initializes a set of items and simulates their quality updates over a number of days.
 */
public class TexttestFixture {

    private static final String HEADER_SEPARATOR = "-------- day ";
    private static final String HEADER_SUFFIX = " --------";
    private static final String ITEM_HEADER = "name, sellIn, quality";
    private static final int DEFAULT_DAYS = 2;

    /**
     * Entry point for the GildedRose test fixture.
     *
     * @param args optional command-line argument specifying the number of days to simulate
     */
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = initializeItems();
        GildedRose gildedRoseApp = new GildedRose(items);

        int simulationDays = getSimulationDays(args);

        runSimulation(gildedRoseApp, items, simulationDays);
    }

    /**
     * Initializes the set of items used in the simulation.
     *
     * @return an array of {@link Item} objects
     */
    private static Item[] initializeItems() {
        return new Item[] {
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
     * Determines the number of days to simulate based on command-line arguments.
     *
     * @param args command-line arguments
     * @return the number of days to simulate
     */
    private static int getSimulationDays(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]) + 1;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input for days. Using default value: " + DEFAULT_DAYS);
            }
        }
        return DEFAULT_DAYS;
    }

    /**
     * Runs the simulation for the specified number of days.
     *
     * @param gildedRoseApp the {@link GildedRose} application instance
     * @param items the array of items to simulate
     * @param days the number of days to simulate
     */
    private static void runSimulation(GildedRose gildedRoseApp, Item[] items, int days) {
        for (int day = 0; day < days; day++) {
            printDayHeader(day);
            printItems(items);
            gildedRoseApp.updateQuality();
        }
    }

    /**
     * Prints the header for the current day.
     *
     * @param day the current day number
     */
    private static void printDayHeader(int day) {
        System.out.println(HEADER_SEPARATOR + day + HEADER_SUFFIX);
        System.out.println(ITEM_HEADER);
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