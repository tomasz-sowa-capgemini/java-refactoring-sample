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
        /// Updates the quality and sell-in values of all items according to their specific rules.
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
                    // Legendary item, does not change
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

        private static void UpdateAgedBrie(Item item)
        {
            IncreaseQuality(item);
        }

        private static void UpdateBackstagePass(Item item)
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

        private static void UpdateRegularItem(Item item)
        {
            DecreaseQuality(item);
        }

        private static void HandleExpiredItem(Item item)
        {
            switch (item.Name)
            {
                case "Aged Brie":
                    IncreaseQuality(item);
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    item.Quality = 0;
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    // No changes for Sulfuras
                    break;

                default:
                    DecreaseQuality(item);
                    break;
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
    }
}