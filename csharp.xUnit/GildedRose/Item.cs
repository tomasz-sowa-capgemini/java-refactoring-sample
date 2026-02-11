namespace GildedRoseKata
{
    /// <summary>
    /// Represents an item with a name, sell-in value, and quality.
    /// </summary>
    public class Item
    {
        /// <summary>
        /// Gets or sets the name of the item.
        /// </summary>
        public string Name { get; set; }

        /// <summary>
        /// Gets or sets the number of days to sell the item.
        /// </summary>
        public int SellIn { get; set; }

        /// <summary>
        /// Gets or sets the quality of the item.
        /// </summary>
        public int Quality { get; set; }
    }
}