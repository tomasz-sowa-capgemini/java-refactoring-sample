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
        /// Verifies the output of the GildedRose program after simulating 30 days.
        /// </summary>
        /// <returns>A verification task for the test output.</returns>
        [Test]
        public Task VerifyThirtyDaysOutputAsync()
        {
            var outputBuilder = new StringBuilder();

            using var outputWriter = new StringWriter(outputBuilder);
            Console.SetOut(outputWriter);

            using var inputReader = new StringReader($"a{Environment.NewLine}");
            Console.SetIn(inputReader);

            Program.Main(new[] { "30" });

            var programOutput = outputBuilder.ToString();

            return Verifier.Verify(programOutput);
        }
    }
}