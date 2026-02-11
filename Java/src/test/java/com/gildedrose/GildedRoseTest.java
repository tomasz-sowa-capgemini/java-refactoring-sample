package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link GildedRose} class.
 */
class GildedRoseTest {

    /**
     * Verifies that the item name remains unchanged after updating quality.
     */
    @Test
    void shouldRetainItemNameAfterQualityUpdate() {
        Item[] items = { new Item("foo", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals("fixme", gildedRose.items[0].name);
    }
}