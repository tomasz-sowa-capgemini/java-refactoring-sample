package com.gildedrose;

/**
 * TexttestFixture serves as a simple test harness for the GildedRose application.
 * It initializes a set of items and simulates their quality updates over a given number of days.
 */
public class TexttestFixture {

    private static final int DEFAULT_DAYS = 2;

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = createInitialItems();
        GildedRose gildedRoseApp = new GildedRose(items);

        int simulationDays = determineSimulationDays(args);

        runSimulation(gildedRoseApp, items, simulationDays);
    }

    /**
     * Creates and returns the initial set of items for the simulation.
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
     * Determines the number of days to run the simulation based on command-line arguments.
     *
     * @param args command-line arguments
     * @return the number of days to simulate
     */
    private static int determineSimulationDays(String[] args) {
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
     * Runs the simulation for the specified number of days, printing item details each day.
     *
     * @param app   the GildedRose application instance
     * @param items the array of items to simulate
     * @param days  the number of days to simulate
     */
    private static void runSimulation(GildedRose app, Item[] items, int days) {
        for (int day = 0; day < days; day++) {
            printDayHeader(day);
            printItems(items);
            app.updateQuality();
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