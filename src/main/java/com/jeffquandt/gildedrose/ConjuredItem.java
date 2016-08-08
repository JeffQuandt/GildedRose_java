package com.jeffquandt.gildedrose;

/**
 * Created by req89790 on 08/05/2016.
 */
public class ConjuredItem extends NormalItem {

    public static final String CONJURED_QUALIFIER = "Conjured";

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality, 2);
    }
}
