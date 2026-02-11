package com.gildedrose;

/**
 * Entry point for the Gilded Rose application.
 * This class initializes a set of items and simulates their quality updates over a number of days.
 */
public class TexttestFixture {

    private static final String HEADER_SEPARATOR = "-------- day ";
    private static final String HEADER_TITLE = "name, sellIn, quality";
    private static final int DEFAULT_DAYS = 2;

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = initializeItems();
        GildedRose app = new GildedRose(items);

        int simulationDays = getSimulationDays(args);

        simulateDays(app, items, simulationDays);
    }

    /**
     * Initializes the list of items for the simulation.
     *
     * @return an array of {@link Item} objects.
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
     * @param args command-line arguments.
     * @return the number of days to simulate.
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
     * @param app   the GildedRose application instance.
     * @param items the array of items to update.
     * @param days  the number of days to simulate.
     */
    private static void simulateDays(GildedRose app, Item[] items, int days) {
        for (int day = 0; day < days; day++) {
            printDayHeader(day);
            printItems(items);
            app.updateQuality();
        }
    }

    /**
     * Prints the header for a given simulation day.
     *
     * @param day the current day number.
     */
    private static void printDayHeader(int day) {
        System.out.println(HEADER_SEPARATOR + day + " --------");
        System.out.println(HEADER_TITLE);
    }

    /**
     * Prints the details of all items.
     *
     * @param items the array of items to print.
     */
    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }
}