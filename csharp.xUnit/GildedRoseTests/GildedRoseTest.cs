using Xunit;
using System.Collections.Generic;
using GildedRoseKata;

namespace GildedRoseTests
{
    /// <summary>
    /// Unit tests for the <see cref="GildedRose"/> class.
    /// </summary>
    public class GildedRoseTest
    {
        [Fact]
        public void UpdateQuality_ShouldMaintainExpectedItemName()
        {
            // Arrange
            IList<Item> items = new List<Item>
            {
                new Item { Name = "foo", SellIn = 0, Quality = 0 }
            };
            var gildedRose = new GildedRose(items);

            // Act
            gildedRose.UpdateQuality();

            // Assert
            Assert.Equal("fixme", items[0].Name);
        }
    }
}