package com.jeffquandt.gildedrose;

import java.util.Arrays;

public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                ItemFactory.instance().buildItem("+5 Dexterity Vest", 10, 20),
                ItemFactory.instance().buildItem(AgedItem.AGED_BRIE, 2, 0),
                ItemFactory.instance().buildItem("Elixir of the Mongoose", 5, 7),
                ItemFactory.instance().buildItem(Item.SULFURAS_HAND_OF_RAGNAROS, 0, 80),
                ItemFactory.instance().buildItem(Item.BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, 15, 20),
                ItemFactory.instance().buildItem("Conjured Mana Cake", 3, 6)
        };

    }

    public void updateQuality() {
        // replace indexed for loop with stream.forEach
        Arrays.stream(items).forEach(item -> item.decrementSellIn());
    }
}
