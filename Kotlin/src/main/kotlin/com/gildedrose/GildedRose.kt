package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val name = items[i].name
            var quality = items[i].quality
            var sellIn = items[i].sellIn
            var backstage = "Backstage passes"
            var sulfuras = "Sulfuras"
            var brie = "Aged Brie"
            var conjured = "Conjured"
            if (quality > 50) {
                quality = 50
            }
            if (name != brie && !name.contains(backstage)) {
                if (quality > 0) {
                    if (!name.contains(sulfuras)) {
                        quality -= 1
                        if (name.contains(conjured)) {
                            quality -= 1
                        }
                    }
                }
            } else {
                if (quality < 50) {
                    quality += 1

                    if (name.contains(backstage)) {
                        if (sellIn < 11) {
                            if (quality < 50) {
                                quality += 1
                            }
                        }

                        if (sellIn < 6) {
                            if (quality < 50) {
                                quality += 1
                            }
                        }
                    }
                }
            }

            if (!name.contains(sulfuras)) {
                sellIn -= 1
            }

            if (sellIn < 0) {
                if (name != brie) {
                    if (!name.contains(backstage)) {
                        if (quality > 0) {
                            if (!name.contains(sulfuras)) {
                                quality -= 1
                                if (name.contains(conjured)) {
                                quality -= 1
                                }
                            }
                        }
                    } else {
                        quality = 0
                    }
                } else {
                    if (quality < 50) {
                        quality += 1
                    }
                }
            }
            items[i].quality = quality
            items[i].sellIn = sellIn
        }
    }

}

