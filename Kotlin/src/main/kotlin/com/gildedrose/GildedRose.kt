package com.gildedrose

class GildedRose(var items: Array<Item>) {
    
    fun qualityDec(quality: Int, name: String, sulfuras:String, conjured:String) : Int{
        var qual = quality
        if (qual > 0 && !name.contains(sulfuras)) {
            qual -= 1
            if (name.contains(conjured)) {
                qual -= 1
            }
        }
        return qual
    }

    fun qualityInc(quality:Int): Int{
        if (quality < 50){
            return quality + 1
        }
        else{
            return quality
        }

    }

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
                quality = qualityDec(quality, name, sulfuras, conjured)

            } else {
                quality = qualityInc(quality)
                if (name.contains(backstage)) {
                    if (sellIn < 11) {
                        quality = qualityInc(quality)
                    }
                    if (sellIn < 6) {
                        quality = qualityInc(quality)
                    }
                }
            }

            if (!name.contains(sulfuras)) {
                sellIn -= 1
            }

            if (sellIn < 0) {
                if (name != brie) {
                    if (!name.contains(backstage)) {
                        quality = qualityDec(quality, name, sulfuras, conjured)
                    } else {
                        quality = 0
                    }
                } else {
                    quality = qualityInc(quality)
                }
            }
            items[i].quality = quality
            items[i].sellIn = sellIn
        }
    }
}

