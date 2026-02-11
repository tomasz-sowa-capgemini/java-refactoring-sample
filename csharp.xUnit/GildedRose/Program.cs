using System;
using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Entry point for the Gilded Rose application.
    /// Initializes items and simulates quality updates over a number of days.
    /// </summary>
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("OMGHAI!");

            IList<Item> items = InitializeItems();
            var gildedRoseApp = new GildedRose(items);

            int simulationDays = GetSimulationDays(args);

            SimulateDays(gildedRoseApp, items, simulationDays);
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
        /// Determines the number of days to simulate based on command-line arguments.
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
        /// Simulates the passage of days, updating item quality and displaying results.
        /// </summary>
        private static void SimulateDays(GildedRose gildedRoseApp, IList<Item> items, int days)
        {
            for (int day = 0; day < days; day++)
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
        /// Displays all items in the inventory.
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