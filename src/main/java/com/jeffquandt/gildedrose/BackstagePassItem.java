package com.jeffquandt.gildedrose;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by req89790 on 08/08/2016.
 */
public class BackstagePassItem extends Item {

    private static final Set<String> backstagePassItemNames = new HashSet<>();

    static {
        backstagePassItemNames.add(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT);
    }

    public BackstagePassItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        backstagePassItemNames.add(name);
    }

    public static Set<String> getBackstagePassItemNames() {
        return Collections.unmodifiableSet(backstagePassItemNames);
    }

    @Override
    protected void updateQuality() {

        int qualityAdjustment = 1;

        if (getSellIn() < 0) {
            qualityAdjustment = getQuality() * -1;
        } else if (getSellIn() < 5) {
            qualityAdjustment = 3;
        } else if (getSellIn() < 10) {
            qualityAdjustment = 2;
        }

        setQuality(enforceQualityBounds(getQuality() + qualityAdjustment));
    }
}
