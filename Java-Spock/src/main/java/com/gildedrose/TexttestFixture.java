package com.gildedrose;

/**
 * Entry point for the Gilded Rose application test fixture.
 * This class simulates the passage of days and displays item states.
 */
public class TexttestFixture {

    private static final String HEADER_SEPARATOR = "-------- day ";
    private static final String HEADER_SUFFIX = " --------";
    private static final String COLUMN_HEADER = "name, sellIn, quality";
    private static final String INITIAL_MESSAGE = "OMGHAI!";
    private static final int DEFAULT_DAYS = 2;

    public static void main(String[] args) {
        System.out.println(INITIAL_MESSAGE);

        Item[] items = createInitialItems();
        GildedRose gildedRoseApp = new GildedRose(items);

        int daysToSimulate = getDaysToSimulate(args);

        simulateDays(gildedRoseApp, items, daysToSimulate);
    }

    /**
     * Creates the initial set of items for the simulation.
     *
     * @return an array of Item objects
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
     * Determines the number of days to simulate based on command-line arguments.
     *
     * @param args command-line arguments
     * @return number of days to simulate
     */
    private static int getDaysToSimulate(String[] args) {
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
     * Simulates the passage of days, printing item states and updating quality.
     *
     * @param gildedRoseApp the GildedRose application instance
     * @param items the array of items to process
     * @param days number of days to simulate
     */
    private static void simulateDays(GildedRose gildedRoseApp, Item[] items, int days) {
        for (int day = 0; day < days; day++) {
            printDayHeader(day);
            printItems(items);
            gildedRoseApp.updateQuality();
        }
    }

    /**
     * Prints the header for the current day.
     *
     * @param day current day number
     */
    private static void printDayHeader(int day) {
        System.out.println(HEADER_SEPARATOR + day + HEADER_SUFFIX);
        System.out.println(COLUMN_HEADER);
    }

    /**
     * Prints the details of all items.
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