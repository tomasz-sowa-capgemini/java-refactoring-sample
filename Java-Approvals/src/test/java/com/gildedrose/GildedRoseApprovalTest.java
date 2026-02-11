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
 * Ensures that the output and item updates remain consistent over time.
 */
@UseReporter(DiffReporter.class)
public class GildedRoseApprovalTest {

    /**
     * Verifies that the GildedRose item update logic behaves as expected for a single item.
     */
    @Test
    public void shouldUpdateSingleItemCorrectly() {
        Item[] items = { new Item("foo", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        Approvals.verifyAll("Items", items);
    }

    /**
     * Verifies that the program output over thirty days matches the approved output.
     */
    @Test
    public void shouldMatchApprovedOutputForThirtyDays() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        Program.main();
        String programOutput = outputStream.toString();

        Approvals.verify(programOutput);
    }
}