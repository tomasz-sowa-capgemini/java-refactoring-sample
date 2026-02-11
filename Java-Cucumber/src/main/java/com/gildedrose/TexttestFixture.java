package com.gildedrose;

/**
 * Entry point for the Gilded Rose application test fixture.
 * <p>
 * This class simulates the passage of days and prints the state of items
 * before and after quality updates.
 */
public class TexttestFixture {

    private static final String HEADER_SEPARATOR = "-------- day ";
    private static final String HEADER_SUFFIX = " --------";
    private static final String ITEM_HEADER = "name, sellIn, quality";
    private static final String INITIAL_MESSAGE = "OMGHAI!";

    public static void main(String[] args) {
        System.out.println(INITIAL_MESSAGE);

        Item[] items = createInitialItems();
        GildedRose gildedRoseApp = new GildedRose(items);

        int totalDays = determineSimulationDays(args);

        simulateDays(gildedRoseApp, items, totalDays);
    }

    /**
     * Creates the initial set of items for the simulation.
     *
     * @return an array of {@link Item} objects
     */
    private static Item[] createInitialItems() {
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
     * Determines how many days to simulate based on command-line arguments.
     *
     * @param args command-line arguments
     * @return number of days to simulate
     */
    private static int determineSimulationDays(String[] args) {
        final int defaultDays = 2;
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]) + 1;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input for days. Using default value: " + defaultDays);
            }
        }
        return defaultDays;
    }

    /**
     * Simulates the passage of days, printing item states and updating quality.
     *
     * @param gildedRoseApp the GildedRose application instance
     * @param items         the array of items to simulate
     * @param totalDays     number of days to simulate
     */
    private static void simulateDays(GildedRose gildedRoseApp, Item[] items, int totalDays) {
        for (int currentDay = 0; currentDay < totalDays; currentDay++) {
            printDayHeader(currentDay);
            printItems(items);
            gildedRoseApp.updateQuality();
        }
    }

    /**
     * Prints the day header for the simulation.
     *
     * @param day current day number
     */
    private static void printDayHeader(int day) {
        System.out.println(HEADER_SEPARATOR + day + HEADER_SUFFIX);
        System.out.println(ITEM_HEADER);
    }

    /**
     * Prints all items in the current simulation state.
     *
     * @param items array of items to print
     */
    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }
}