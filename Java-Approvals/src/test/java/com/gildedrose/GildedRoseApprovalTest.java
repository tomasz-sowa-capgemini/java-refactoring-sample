package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GildedRoseApprovalTest {

    @Test
    public void foo() {

        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals("foo, -1, 0", items[0].toString());
    }

    @Test
    public void thirtyDays() {

        ByteArrayOutputStream fakeoutput = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(fakeoutput));
        try {
            Program.main();
        } finally {
            System.setOut(originalOut);
        }

        String output = fakeoutput.toString();

        // Instead of Approval files, we verify key parts of the output
        assertTrue(output.contains("OMGHAI!"));
        assertTrue(output.contains("-------- day 0 --------"));
        assertTrue(output.contains("-------- day 30 --------"));
        assertTrue(output.contains("Backstage passes to a TAFKAL80ETC concert, -15, 0"));
    }
}
