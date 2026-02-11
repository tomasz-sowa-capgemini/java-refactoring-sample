using GildedRoseKata;
using System;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using VerifyNUnit;
using NUnit.Framework;

namespace GildedRoseTests
{
    /// <summary>
    /// Contains approval tests for the GildedRose application.
    /// </summary>
    public class ApprovalTest
    {
        /// <summary>
        /// Runs the GildedRose program for thirty days and verifies the output.
        /// </summary>
        [Test]
        public async Task VerifyThirtyDaysOutputAsync()
        {
            var outputBuilder = new StringBuilder();

            using var outputWriter = new StringWriter(outputBuilder);
            Console.SetOut(outputWriter);
            Console.SetIn(new StringReader($"a{Environment.NewLine}"));

            Program.Main(new[] { "30" });

            var programOutput = outputBuilder.ToString();

            await Verifier.Verify(programOutput);
        }
    }
}