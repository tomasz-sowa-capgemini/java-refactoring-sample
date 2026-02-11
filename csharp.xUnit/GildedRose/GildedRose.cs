using System.Collections.Generic;

namespace GildedRoseKata
{
    /// <summary>
    /// Represents the Gilded Rose inventory system responsible for updating item quality and sell-in values.
    /// </summary>
    public class GildedRose
    {
        private readonly IList<Item> _items;

        public GildedRose(IList<Item> items)
        {
            _items = items;
        }

        /// <summary>
        /// Updates the quality and sell-in values of all items in the inventory according to business rules.
        /// </summary>
        public void UpdateQuality()
        {
            foreach (var item in _items)
            {
                UpdateItemQuality(item);
            }
        }

        /// <summary>
        /// Updates the quality and sell-in values for a single item.
        /// </summary>
        /// <param name="item">The item to update.</param>
        private static void UpdateItemQuality(Item item)
        {
            if (IsLegendaryItem(item))
            {
                return;
            }

            AdjustQualityBeforeSellIn(item);
            item.SellIn--;

            if (item.SellIn < 0)
            {
                AdjustQualityAfterSellIn(item);
            }
        }

        private static void AdjustQualityBeforeSellIn(Item item)
        {
            if (IsAgedBrie(item))
            {
                IncreaseQuality(item);
            }
            else if (IsBackstagePass(item))
            {
                IncreaseQuality(item);
                if (item.SellIn < 11) IncreaseQuality(item);
                if (item.SellIn < 6) IncreaseQuality(item);
            }
            else
            {
                DecreaseQuality(item);
            }
        }

        private static void AdjustQualityAfterSellIn(Item item)
        {
            if (IsAgedBrie(item))
            {
                IncreaseQuality(item);
            }
            else if (IsBackstagePass(item))
            {
                item.Quality = 0;
            }
            else
            {
                DecreaseQuality(item);
            }
        }

        private static void IncreaseQuality(Item item)
        {
            if (item.Quality < 50)
            {
                item.Quality++;
            }
        }

        private static void DecreaseQuality(Item item)
        {
            if (item.Quality > 0)
            {
                item.Quality--;
            }
        }

        private static bool IsAgedBrie(Item item) => item.Name == "Aged Brie";
        private static bool IsBackstagePass(Item item) => item.Name == "Backstage passes to a TAFKAL80ETC concert";
        private static bool IsLegendaryItem(Item item) => item.Name == "Sulfuras, Hand of Ragnaros";
    }
}