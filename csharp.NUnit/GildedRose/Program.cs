using System;
using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Entry point for the Gilded Rose application.
    /// Initializes items and runs the quality update simulation.
    /// </summary>
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("OMGHAI!");

            IList<Item> items = InitializeItems();
            var app = new GildedRose(items);

            int totalDays = GetSimulationDays(args);

            RunSimulation(app, items, totalDays);
        }

        /// <summary>
        /// Initializes the list of items for the Gilded Rose inventory.
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

            if (args.Length > 0 && int.TryParse(args[0], out int parsedDays))
            {
                return parsedDays + 1;
            }

            return defaultDays;
        }

        /// <summary>
        /// Runs the Gilded Rose simulation for the specified number of days.
        /// </summary>
        private static void RunSimulation(GildedRose app, IList<Item> items, int totalDays)
        {
            for (int currentDay = 0; currentDay < totalDays; currentDay++)
            {
                Console.WriteLine($"-------- day {currentDay} --------");
                Console.WriteLine("name, sellIn, quality");

                foreach (var item in items)
                {
                    Console.WriteLine($"{item.Name}, {item.SellIn}, {item.Quality}");
                }

                Console.WriteLine();
                app.UpdateQuality();
            }
        }
    }
}