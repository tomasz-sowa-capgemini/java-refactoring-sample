using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Represents the Gilded Rose inventory system responsible for updating item quality and sell-in values.
    /// </summary>
    public class GildedRose
    {
        private readonly IList<Item> _items;

        private const string AgedBrie = "Aged Brie";
        private const string BackstagePass = "Backstage passes to a TAFKAL80ETC concert";
        private const string Sulfuras = "Sulfuras, Hand of Ragnaros";
        private const int MaxQuality = 50;
        private const int MinQuality = 0;

        /// <summary>
        /// Initializes a new instance of the <see cref="GildedRose"/> class.
        /// </summary>
        /// <param name="items">The list of items to manage.</param>
        public GildedRose(IList<Item> items)
        {
            _items = items;
        }

        /// <summary>
        /// Updates the quality and sell-in values for all items in the inventory.
        /// </summary>
        public void UpdateQuality()
        {
            foreach (var item in _items)
            {
                UpdateItemQuality(item);
                UpdateItemSellIn(item);
                HandleExpiredItem(item);
            }
        }

        private static void UpdateItemQuality(Item item)
        {
            if (item.Name == AgedBrie)
            {
                IncreaseQuality(item);
            }
            else if (item.Name == BackstagePass)
            {
                UpdateBackstagePassQuality(item);
            }
            else if (item.Name != Sulfuras)
            {
                DecreaseQuality(item);
            }
        }

        private static void UpdateItemSellIn(Item item)
        {
            if (item.Name != Sulfuras)
            {
                item.SellIn--;
            }
        }

        private static void HandleExpiredItem(Item item)
        {
            if (item.SellIn >= 0)
            {
                return;
            }

            if (item.Name == AgedBrie)
            {
                IncreaseQuality(item);
            }
            else if (item.Name == BackstagePass)
            {
                item.Quality = MinQuality;
            }
            else if (item.Name != Sulfuras)
            {
                DecreaseQuality(item);
            }
        }

        private static void UpdateBackstagePassQuality(Item item)
        {
            IncreaseQuality(item);

            if (item.SellIn < 11)
            {
                IncreaseQuality(item);
            }

            if (item.SellIn < 6)
            {
                IncreaseQuality(item);
            }
        }

        private static void IncreaseQuality(Item item)
        {
            if (item.Quality < MaxQuality)
            {
                item.Quality++;
            }
        }

        private static void DecreaseQuality(Item item)
        {
            if (item.Quality > MinQuality)
            {
                item.Quality--;
            }
        }
    }
}