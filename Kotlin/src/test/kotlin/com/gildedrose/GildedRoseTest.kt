package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test fun normal_item_behaviour() {
        //test name, decrement of sellIn and quality for normal item
        val items = arrayOf<Item>(Item("fool", 1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fool", app.items[0].name)
        assertEquals(0, app.items[0].sellIn)
        assertEquals(9, app.items[0].quality)
    }

    @Test fun normal_item_decrementing_quality() {
        val items = arrayOf<Item>(Item("fool", 0, 9))
        val app = GildedRose(items)
        //test double degradation past sellIn
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(7, app.items[0].quality)
    }

    @Test fun quality_not_exceeding_50() {
        //test max quality of 50
        val items = arrayOf<Item>(Item("priceless", 5, 52))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(49, app.items[0].quality)
    }

    @Test fun quality_never_negative() {
        //test quality never negative
        val items = arrayOf<Item>(Item("terrible", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test fun sulfuras_behaviour() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 30, 30), Item("Sulfuras, Hand of Ragnaros", 0, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(30, app.items[0].sellIn)
        assertEquals(30, app.items[0].quality)
        assertEquals(0, app.items[1].sellIn)
        assertEquals(30, app.items[1].quality)
    }

    @Test fun brie_behaviour() {
        val items = arrayOf<Item>(Item("Aged Brie", 10, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(31, app.items[0].quality)
    }

    @Test fun backstage_passes_behaviour() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 20, 20), Item("Backstage passes to a TAFKAL80ETC concert", 10, 30), Item("Backstage passes to a TAFKAL80ETC concert", 5, 40), Item("Backstage passes to a TAFKAL80ETC concert", 0, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(21, app.items[0].quality)
        assertEquals(32, app.items[1].quality)
        assertEquals(43, app.items[2].quality)
        assertEquals(0, app.items[3].quality)
    }

    @Test fun conjured_normal_item_behaviour() {
        val items = arrayOf<Item>(Item("Conjured fool", 5, 30), Item("Conjured fool", 0, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(28, app.items[0].quality)
        assertEquals(6, app.items[1].quality)
    }

    @Test fun conjured_sulfuras_behaviour() {
        val items = arrayOf<Item>(Item("Conjured Sulfuras, Hand of Ragnaros", 5, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(30, app.items[0].quality)
    }

}


