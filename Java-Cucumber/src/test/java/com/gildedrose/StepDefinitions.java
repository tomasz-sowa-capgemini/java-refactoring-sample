package com.gildedrose;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for GildedRose feature tests.
 * This class defines the steps used in Cucumber scenarios
 * to validate the behavior of the GildedRose application.
 */
public class StepDefinitions {

    private static final int DEFAULT_SELL_IN = 0;
    private static final int DEFAULT_QUALITY = 0;

    private Item[] items = new Item[1];
    private GildedRose gildedRoseApp;

    /**
     * Initializes the GildedRose application with a single item.
     *
     * @param itemName the name of the item to initialize
     */
    @Given("The item as {string}")
    public void givenItemName(String itemName) {
        items[0] = new Item(itemName, DEFAULT_SELL_IN, DEFAULT_QUALITY);
        gildedRoseApp = new GildedRose(items);
    }

    /**
     * Updates the quality of the items in the GildedRose application.
     */
    @When("I update the quality")
    public void whenUpdateQuality() {
        gildedRoseApp.updateQuality();
    }

    /**
     * Verifies that the item name matches the expected value after update.
     *
     * @param expectedItemName the expected name of the item
     */
    @Then("I should get item as {string}")
    public void thenVerifyItemName(String expectedItemName) {
        assertEquals(expectedItemName, gildedRoseApp.items[0].name);
    }
}