using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Represents the Gilded Rose inventory system responsible for updating item quality and sell-in values.
    /// </summary>
    public class GildedRose
    {
        private readonly IList<Item> _items;

        /// <summary>
        /// Initializes a new instance of the <see cref="GildedRose"/> class with a list of items.
        /// </summary>
        /// <param name="items">The collection of items managed by the Gilded Rose.</param>
        public GildedRose(IList<Item> items)
        {
            _items = items;
        }

        /// <summary>
        /// Updates the quality and sell-in values of all items according to the Gilded Rose rules.
        /// </summary>
        public void UpdateQuality()
        {
            foreach (var item in _items)
            {
                UpdateItemQuality(item);
            }
        }

        private static void UpdateItemQuality(Item item)
        {
            switch (item.Name)
            {
                case "Aged Brie":
                    UpdateAgedBrie(item);
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    UpdateBackstagePass(item);
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    // Legendary item, no changes to quality or sell-in
                    break;

                default:
                    UpdateRegularItem(item);
                    break;
            }

            if (item.Name != "Sulfuras, Hand of Ragnaros")
            {
                item.SellIn--;
            }

            if (item.SellIn < 0)
            {
                HandleExpiredItem(item);
            }
        }

        private static void UpdateRegularItem(Item item)
        {
            if (item.Quality > 0)
            {
                item.Quality--;
            }
        }

        private static void UpdateAgedBrie(Item item)
        {
            if (item.Quality < 50)
            {
                item.Quality++;
            }
        }

        private static void UpdateBackstagePass(Item item)
        {
            if (item.Quality < 50)
            {
                item.Quality++;

                if (item.SellIn < 11 && item.Quality < 50)
                {
                    item.Quality++;
                }

                if (item.SellIn < 6 && item.Quality < 50)
                {
                    item.Quality++;
                }
            }
        }

        private static void HandleExpiredItem(Item item)
        {
            switch (item.Name)
            {
                case "Aged Brie":
                    if (item.Quality < 50)
                    {
                        item.Quality++;
                    }
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    item.Quality = 0;
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    // No changes for Sulfuras
                    break;

                default:
                    if (item.Quality > 0)
                    {
                        item.Quality--;
                    }
                    break;
            }
        }
    }
}