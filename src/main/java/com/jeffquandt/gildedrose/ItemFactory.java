package com.jeffquandt.gildedrose;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by req89790 on 08/05/2016.
 */
public class ItemFactory {

    private static final ItemFactory instance = new ItemFactory();

    public static ItemFactory instance() {
        return instance;
    }

    public Item buildItem(String name, int sellIn, int quality) {

        Item item;

        // name cannot be null

        if (StringUtils.trimToEmpty(name).contains(ConjuredItem.CONJURED_QUALIFIER)) {
            item = new ConjuredItem(name, sellIn, quality);
        } else if (LegendaryItem.getLegendaryItemNames().contains(StringUtils.trimToEmpty(name))) {
            item = new LegendaryItem(name, sellIn, quality);
        } else if (AgedItem.getAgedItemNames().contains(StringUtils.trimToEmpty(name))) {
            item = new AgedItem(name, sellIn, quality);
        } else if (BackstagePassItem.getBackstagePassItemNames().contains(StringUtils.trimToEmpty(name))) {
            item = new BackstagePassItem(name, sellIn, quality);
        } else {
            item = new NormalItem(name, sellIn, quality);
        }

        return item;
    }
}
