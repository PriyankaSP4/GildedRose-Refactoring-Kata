package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test fun normal_item_name_stays_same() {
        //test name, decrement of sellIn and quality for normal item
        val items = arrayOf<Item>(Item("fool", 1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fool", app.items[0].name)
    }

    @Test fun normal_item_sellIn_decrement() {
        //test name, decrement of sellIn and quality for normal item
        val items = arrayOf<Item>(Item("fool", 1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test fun normal_item_quality_decrement_before_sellIn_date_passes() {
        //test name, decrement of sellIn and quality for normal item
        val items = arrayOf<Item>(Item("fool", 1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, app.items[0].quality)
    }

    @Test fun normal_item_decrementing_quality_after_sellIn_date_passes() {
        //test double degradation past sellIn
        val items = arrayOf<Item>(Item("fool", 0, 9))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(7, app.items[0].quality)
    }

    @Test fun quality_not_exceeding_50() {
        //test max quality of 50
        val items = arrayOf<Item>(Item("Aged Brie", 5, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test fun quality_never_negative() {
        //test quality never negative
        val items = arrayOf<Item>(Item("terrible", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test fun sulfuras_unchanged_quality() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 30, 30), Item("Sulfuras, Hand of Ragnaros", 0, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(30, app.items[0].quality)
        assertEquals(30, app.items[1].quality)
    }

    @Test fun sulfuras_unchanged_sellIn() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 30, 30), Item("Sulfuras, Hand of Ragnaros", 0, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(30, app.items[0].sellIn)
        assertEquals(0, app.items[1].sellIn)
    }

    @Test fun brie_increasing_in_quality() {
        val items = arrayOf<Item>(Item("Aged Brie", 10, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(50, app.items[0].quality)
    }

    @Test fun backstage_passes_quality_rules_dep_on_sellIn() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 20, 20), Item("Backstage passes to a TAFKAL80ETC concert", 10, 30), Item("Backstage passes to a TAFKAL80ETC concert", 5, 40), Item("Backstage passes to a TAFKAL80ETC concert", 0, 50))
        val app = GildedRose(items)
        app.updateQuality()
        //sellIn > 10, quality increments by 1
        assertEquals(21, app.items[0].quality)
        //6 < sellIn < 10, quality increments by 2
        assertEquals(32, app.items[1].quality)
        // sellIn < 6, quality increments by 3
        assertEquals(43, app.items[2].quality)
        //sellIn < 0, quality drops to 0
        assertEquals(0, app.items[3].quality)
    }

    @Test fun conjured_normal_item_degrading_twice_as_fast() {
        val items = arrayOf<Item>(Item("Conjured fool", 5, 30), Item("Conjured fool", 0, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(28, app.items[0].quality)
        assertEquals(6, app.items[1].quality)
    }

    @Test fun conjured_sulfuras_quality_still_staying_same() {
        val items = arrayOf<Item>(Item("Conjured Sulfuras, Hand of Ragnaros", 5, 30))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(30, app.items[0].quality)
    }

}


