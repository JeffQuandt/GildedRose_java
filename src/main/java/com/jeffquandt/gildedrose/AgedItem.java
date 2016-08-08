package com.jeffquandt.gildedrose;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by req89790 on 08/08/2016.
 */
public class AgedItem extends Item {

    private static final Set<String> agedItemNames = new HashSet<>();

    static {
        agedItemNames.add(AGED_BRIE);
    }

    public AgedItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        agedItemNames.add(name);
    }

    public static Set<String> getAgedItemNames() {
        return Collections.unmodifiableSet(agedItemNames);
    }

    @Override
    protected void updateQuality() {

        int qualityAdjustment = 1;

        if (getSellIn() < 0) {
            qualityAdjustment = 2;
        }
        setQuality(enforceQualityBounds(getQuality() + qualityAdjustment));
    }
}
