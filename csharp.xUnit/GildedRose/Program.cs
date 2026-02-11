using System;
using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Entry point for the Gilded Rose application.
    /// </summary>
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("OMGHAI!");

            IList<Item> items = InitializeItems();
            var gildedRoseApp = new GildedRose(items);

            int totalDays = GetSimulationDays(args);

            RunSimulation(gildedRoseApp, items, totalDays);
        }

        /// <summary>
        /// Initializes the list of items used in the Gilded Rose simulation.
        /// </summary>
        private static IList<Item> InitializeItems()
        {
            return new List<Item>
            {
                new Item { Name = "+5 Dexterity Vest", SellIn = 10, Quality = 20 },
                new Item { Name = "Aged Brie", SellIn = 2, Quality = 0 },
                new Item { Name = "Elixir of the Mongoose", SellIn = 5, Quality = 7 },
                new Item { Name = "Sulfuras, Hand of Ragnaros", SellIn = 0, Quality = 80 },
                new Item { Name = "Sulfuras, Hand of Ragnaros", SellIn = -1, Quality = 80 },
                new Item
                {
                    Name = "Backstage passes to a TAFKAL80ETC concert",
                    SellIn = 15,
                    Quality = 20
                },
                new Item
                {
                    Name = "Backstage passes to a TAFKAL80ETC concert",
                    SellIn = 10,
                    Quality = 49
                },
                new Item
                {
                    Name = "Backstage passes to a TAFKAL80ETC concert",
                    SellIn = 5,
                    Quality = 49
                },
                // This conjured item does not work properly yet
                new Item { Name = "Conjured Mana Cake", SellIn = 3, Quality = 6 }
            };
        }

        /// <summary>
        /// Determines the number of days to run the simulation based on command-line arguments.
        /// </summary>
        private static int GetSimulationDays(string[] args)
        {
            const int defaultDays = 2;

            if (args.Length == 0)
            {
                return defaultDays;
            }

            if (int.TryParse(args[0], out int parsedDays))
            {
                return parsedDays + 1;
            }

            Console.WriteLine("Invalid input for days. Using default value.");
            return defaultDays;
        }

        /// <summary>
        /// Runs the Gilded Rose simulation for the specified number of days.
        /// </summary>
        private static void RunSimulation(GildedRose gildedRoseApp, IList<Item> items, int totalDays)
        {
            for (int day = 0; day < totalDays; day++)
            {
                DisplayDayHeader(day);
                DisplayItems(items);
                gildedRoseApp.UpdateQuality();
            }
        }

        /// <summary>
        /// Displays the header for the current simulation day.
        /// </summary>
        private static void DisplayDayHeader(int day)
        {
            Console.WriteLine($"-------- day {day} --------");
            Console.WriteLine("name, sellIn, quality");
        }

        /// <summary>
        /// Displays the current state of all items.
        /// </summary>
        private static void DisplayItems(IEnumerable<Item> items)
        {
            foreach (var item in items)
            {
                Console.WriteLine($"{item.Name}, {item.SellIn}, {item.Quality}");
            }

            Console.WriteLine();
        }
    }
}