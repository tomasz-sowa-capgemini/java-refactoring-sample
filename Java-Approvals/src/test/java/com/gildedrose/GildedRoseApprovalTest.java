package com.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Approval tests for the GildedRose application.
 * Verifies expected behavior of item quality updates and program output.
 */
@UseReporter(DiffReporter.class)
public class GildedRoseApprovalTest {

    /**
     * Verifies that a single item is updated correctly after one quality update.
     */
    @Test
    public void shouldUpdateSingleItemQuality() {
        Item[] items = { new Item("foo", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Approvals.verifyAll("Items", items);
    }

    /**
     * Verifies the output of the program over thirty days of simulation.
     */
    @Test
    public void shouldVerifyThirtyDaysProgramOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        Program.main();

        String programOutput = outputStream.toString();
        Approvals.verify(programOutput);
    }
}