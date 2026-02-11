using Xunit;
using System.Collections.Generic;
using GildedRoseKata;

namespace GildedRoseTests
{
    /// <summary>
    /// Unit tests for the GildedRose application.
    /// </summary>
    public class GildedRoseTests
    {
        /// <summary>
        /// Verifies that the GildedRose UpdateQuality method behaves as expected for a basic item.
        /// </summary>
        [Fact]
        public void UpdateQuality_ShouldMaintainExpectedItemName()
        {
            // Arrange
            IList<Item> items = new List<Item>
            {
                new Item { Name = "foo", SellIn = 0, Quality = 0 }
            };

            var gildedRoseApp = new GildedRose(items);

            // Act
            gildedRoseApp.UpdateQuality();

            // Assert
            Assert.Equal("fixme", items[0].Name);
        }
    }
}