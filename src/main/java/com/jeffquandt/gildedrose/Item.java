/**
 *
 */
package com.jeffquandt.gildedrose;

public abstract class Item {

    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    private String name;
    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }


    /**
     * Centralize Quality min/max bound enforcement
     *
     * @param quality - proposed new quality
     * @return - min/max bounded quality
     */
    protected int enforceQualityBounds(int quality) {

        int returnQuality = Integer.min(quality, MAX_QUALITY);
        returnQuality = Integer.max(returnQuality, MIN_QUALITY);

        return returnQuality;
    }

    protected abstract void updateQuality();

    public void decrementSellIn() {
        setSellIn(getSellIn() - 1);
        updateQuality();
    }
}