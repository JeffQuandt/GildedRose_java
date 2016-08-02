package com.alexaitken.gildedrose

import spock.lang.Specification

public class InventoryTest extends Specification {
//	new Item("+5 Dexterity Vest", 10, 20), 
//	new Item("Aged Brie", 2, 0),
//	new Item("Elixir of the Mongoose", 5, 7),
//	new Item("Sulfuras, Hand of Ragnaros", 0, 80),
//	new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//	new Item("Conjured Mana Cake", 3, 6)

    def "Sulfuras, Hand of Ragnaros never changes in Quality"() {
        given: "Sulfuras, Hand of Ragnaros"
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80)

        Inventory sut = new Inventory((Item[]) Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 0, 80)).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()


        then: "The quality of Sulfuras, Hand of Ragnaros has not changed"
        assert 80 == sulfuras.getQuality()
    }

    def "Sulfuras, Hand of Ragnaros never change sellIn"() {

        given: "Sulfuras, Hand of Ragnaros"
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80)

        Inventory sut = new Inventory((Item[]) Arrays.asList(sulfuras).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The sellin of Sulfuras, Hand of Ragnaros remains 0"
        0 == sulfuras.sellIn

    }

    def "SellIn of normal items is decreased by one"() {

        given: "A perfectly normal +5 Dexterity vest"
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20)

        Inventory sut = new Inventory((Item[]) Arrays.asList(normalItem).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The sellIn of the normal item has decreased by one."
        9 == normalItem.sellIn
    }

    def "should lower the quality by one for normal items"() {

        given: "A perfectly normal +5 Dexterity vest"
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20)

        Inventory sut = new Inventory((Item[]) Arrays.asList(normalItem).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the normal item has decreased by one."
        19 == normalItem.quality
    }

    def "should not lower the quality below zero"() {

        given: "A perfectly normal +5 Dexterity vest with quality of zero"
        Item normalItem = new Item("+5 Dexterity Vest", 10, 0)

        Inventory sut = new Inventory((Item[]) Arrays.asList(normalItem).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the normal item has updated to zero, but not below."
        0 == normalItem.quality
    }

    def "should lower the quality twice as fast once the sell in date has passed"() {

        given: "A perfectly normal +5 Dexterity vest passed its sellIn threshold"
        Item normalItem = new Item("+5 Dexterity Vest", -1, 25)

        Inventory sut = new Inventory((Item[]) Arrays.asList(normalItem).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the normal item has decreased by two."
        23 == normalItem.quality
    }


    def "should increase the quality of aged brie as it gets older"() {

        given: "A fine Aged Brie"
        Item agedBrie = new Item("Aged Brie", 10, 25)

        Inventory sut = new Inventory((Item[]) Arrays.asList(agedBrie).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Aged Brie has increased by one"
        26 == agedBrie.quality
    }

    def "should increase the quality of Aged Brie twice as fast after sellIn date"() {

        given: "A fine Aged Brie passed its sellIn threshold"
        Item normalItem = new Item("Aged Brie", -1, 25)

        Inventory sut = new Inventory((Item[]) Arrays.asList(normalItem).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Aged Brie has increased by two."
        27 == normalItem.quality
    }


    def "should not increase the quality of aged brie over 50"() {

        given: "A fine Aged Brie of exceptional quality"
        Item agedBrie = new Item("Aged Brie", 10, 50)

        Inventory sut = new Inventory((Item[]) Arrays.asList(agedBrie).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Aged Brie should not increase"
        50 == agedBrie.quality
    }

    def "should lower backstage passes to zero quality once concert has happened"() {

        given: "Backstage passes to L80 Elite Tauren Chieften concert from yesterday"
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)

        Inventory sut = new Inventory((Item[]) Arrays.asList(backStagePass).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Backstage passes equals zero."
        0 == backStagePass.quality
    }

    def "should increase backstage passes quality by 1 when the concert is more than 10 days away"() {

        given: "Backstage passes to L80 Elite Tauren Chieften concert"
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)

        Inventory sut = new Inventory((Item[]) Arrays.asList(backStagePass).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Backstage passes increases by one."
        21 == backStagePass.quality
    }

    def "should increase backstage passes quality by 2 when the concert is 10 days or less away"() {

        given: "Backstage passes to L80 Elite Tauren Chieften concert that is happening soon"
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27)

        Inventory sut = new Inventory((Item[]) Arrays.asList(backStagePass).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the Backstage passes increases by two."
        29 == backStagePass.quality
    }

    def "should increase backstage passes quality by 3 when the concert is 5 days or less away"() {

        given: "Backstage passes to L80 Elite Tauren Chieften concert happening very soon"
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44)

        Inventory sut = new Inventory((Item[]) Arrays.asList(backStagePass).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the backstage passes increases by three."
        47 == backStagePass.quality
    }

    def "should not increase backstage passes above a quality of 50"() {

        given: "Backstage passes to L80 Elite Tauren Chieften concerts with a quality of 50"
        Item backStagePassMoreThan10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50)

        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)
        Item backStagePass5DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)

        Inventory sut = new Inventory((Item[]) Arrays.asList(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway).toArray())

        when: "Updating quality of Inventory"
        sut.updateQuality()

        then: "The quality of the passes does not exceed 50."
        50 == backStagePassMoreThan10DaysAway.quality
        50 == backStagePass10DaysAway.quality
        50 == backStagePass5DaysAway.quality
    }

}
