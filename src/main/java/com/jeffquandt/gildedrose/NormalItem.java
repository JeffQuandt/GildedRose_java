package com.jeffquandt.gildedrose;

/**
 * Created by req89790 on 08/05/2016.
 */
public class NormalItem extends Item {

    protected final int qualityAdjustment;

    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        qualityAdjustment = 1;
    }

    protected NormalItem(String name, int sellIn, int quality, int qualityAdjustment) {
        super(name, sellIn, quality);
        this.qualityAdjustment = qualityAdjustment;
    }

    @Override
    protected void updateQuality() {

        int qualityAdjustment = this.qualityAdjustment;

        // if Sellin is passed, decrease quality twice
        if (getSellIn() < 0) {
            qualityAdjustment *= 2;
        }

        setQuality(enforceQualityBounds(getQuality() - qualityAdjustment));
    }
}
