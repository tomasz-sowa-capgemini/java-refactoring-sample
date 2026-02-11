using GildedRoseKata;
using System;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using VerifyXunit;
using Xunit;

namespace GildedRoseTests
{
    /// <summary>
    /// Contains approval tests for the GildedRose application.
    /// </summary>
    public class ApprovalTest
    {
        /// <summary>
        /// Verifies that the quality update logic works correctly for a single item.
        /// </summary>
        [Fact]
        public Task VerifySingleItemQualityUpdateAsync()
        {
            var items = new[] { new Item { Name = "foo", SellIn = 0, Quality = 0 } };
            var gildedRoseApp = new GildedRose(items);

            gildedRoseApp.UpdateQuality();

            return Verifier.Verify(items);
        }

        /// <summary>
        /// Verifies the program output after simulating 30 days.
        /// </summary>
        [Fact]
        public Task VerifyThirtyDaysOutputAsync()
        {
            var outputBuilder = new StringBuilder();
            Console.SetOut(new StringWriter(outputBuilder));
            Console.SetIn(new StringReader($"a{Environment.NewLine}"));

            Program.Main(new[] { "30" });

            var output = outputBuilder.ToString();
            return Verifier.Verify(output);
        }
    }
}