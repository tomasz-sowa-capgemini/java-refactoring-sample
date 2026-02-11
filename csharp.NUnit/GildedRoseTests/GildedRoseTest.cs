using System.Collections.Generic;
using GildedRoseKata;
using NUnit.Framework;

namespace GildedRoseTests
{
    /// <summary>
    /// Unit tests for the <see cref="GildedRose"/> class.
    /// </summary>
    [TestFixture]
    public class GildedRoseTests
    {
        /// <summary>
        /// Verifies that the <see cref="GildedRose.UpdateQuality"/> method updates item properties as expected.
        /// </summary>
        [Test]
        public void UpdateQuality_ShouldUpdateItemNameCorrectly()
        {
            // Arrange
            var items = new List<Item>
            {
                new Item { Name = "foo", SellIn = 0, Quality = 0 }
            };

            var gildedRoseApp = new GildedRose(items);

            // Act
            gildedRoseApp.UpdateQuality();

            // Assert
            Assert.That(items[0].Name, Is.EqualTo("fixme"));
        }
    }
}