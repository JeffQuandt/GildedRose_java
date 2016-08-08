package com.jeffquandt.gildedrose;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by req89790 on 08/08/2016.
 */
public class LegendaryItem extends Item {

    private static final Set<String> legendaryItemNames = new HashSet<>();

    static {

        // Small issue that new Legendary items require a code change, as this list is only updated
        // in static initializer, and constructor. But constructor is only called from Factory.
        legendaryItemNames.add(SULFURAS_HAND_OF_RAGNAROS);
    }

    public LegendaryItem(String name, int sellIn, int quality) {

        super(name, sellIn, quality);
        legendaryItemNames.add(name);
    }

    @Override
    protected void updateQuality() {
        // Legendary items never update quality
    }

    @Override
    public void decrementSellIn() {
        // Legendary items never expire
    }

    public static Set<String> getLegendaryItemNames() {

        return Collections.unmodifiableSet(legendaryItemNames);
    }
}
