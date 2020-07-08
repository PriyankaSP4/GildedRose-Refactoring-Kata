package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun qualityDec(quality: Int) : Int{
        if (quality > 0){
            return quality - 1
        }
        else{
            return quality
        }
    }

    fun qualityInc(quality:Int): Int{
        if (quality < 50){
            return quality + 1
        }
        else{
            return quality
        }
    }

    fun sellInDec(sellIn: Int) : Int{
        return sellIn - 1
    }

    fun normal(item: Item) {
        item.quality = qualityDec(item.quality)
        item.sellIn = sellInDec(item.sellIn)
        if (item.sellIn < 0) {
            item.quality = qualityDec(item.quality)
        }
    }

    fun brie(item: Item){
        item.quality = qualityInc(item.quality)
        item.sellIn = sellInDec(item.sellIn)
    }

    fun backstage(item: Item){
        item.quality = qualityInc(item.quality)

        if (item.sellIn < 11) {
            item.quality = qualityInc(item.quality)
        }
        if (item.sellIn < 6) {
            item.quality = qualityInc(item.quality)
        }

        if (item.sellIn < 1) {
            item.quality = 0
        }
    }

    fun conjured(item: Item){
        item.quality = qualityDec(item.quality)
        item.quality = qualityDec(item.quality)
        item.sellIn = sellInDec(item.sellIn)
        if (item.sellIn < 0) {
            item.quality = qualityDec(item.quality)
            item.quality = qualityDec(item.quality)
        }
    }

    fun maxQual(item: Item){
        if (item.quality > 50) {
            item.quality = 50
        }
    }

    fun updateQuality() {
        for (item in items) {
            val name = item.name
            val backstage = "Backstage passes"
            val sulfuras = "Sulfuras"
            val brie = "Aged Brie"
            val conjured = "Conjured"

            maxQual(item)

            if (name == brie){
                brie(item)
            }
            else if (name.contains(backstage)){
                backstage(item)
            }
            else if (name.contains(sulfuras)){
                break
            }
            else if (name.contains(conjured)){
                conjured(item)
            }
            else{
                normal(item)
            }
        }
    }
}
