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

    fun normal(i:Int) {
        items[i].quality = qualityDec(items[i].quality)
        items[i].sellIn = sellInDec(items[i].sellIn)
        if (items[i].sellIn < 0) {
            items[i].quality = qualityDec(items[i].quality)
        }
    }

    fun brie(i:Int){
        items[i].quality = qualityInc(items[i].quality)
        items[i].sellIn = sellInDec(items[i].sellIn)
    }

    fun backstage(i:Int){
        items[i].quality = qualityInc(items[i].quality)

        if (items[i].sellIn < 11) {
            items[i].quality = qualityInc(items[i].quality)
        }
        if (items[i].sellIn < 6) {
            items[i].quality = qualityInc(items[i].quality)
        }

        if (items[i].sellIn < 1) {
            items[i].quality = 0
        }
    }

    fun conjured(i:Int){
        items[i].quality = qualityDec(items[i].quality)
        items[i].quality = qualityDec(items[i].quality)
        items[i].sellIn = sellInDec(items[i].sellIn)
        if (items[i].sellIn < 0) {
            items[i].quality = qualityDec(items[i].quality)
            items[i].quality = qualityDec(items[i].quality)
        }
    }

    fun maxQual(i:Int){
        if (items[i].quality > 50) {
            items[i].quality = 50
        }
    }

    fun updateQuality() {
        for (i in items.indices) {
            val name = items[i].name
            var backstage = "Backstage passes"
            var sulfuras = "Sulfuras"
            var brie = "Aged Brie"
            var conjured = "Conjured"

            maxQual(i)

            if (name == brie){
                brie(i)
            }
            else if (name.contains(backstage)){
                backstage(i)
            }
            else if (name.contains(sulfuras)){
                break
            }
            else if (name.contains(conjured)){
                conjured(i)
            }
            else{
                normal(i)
            }
        }
    }
}

