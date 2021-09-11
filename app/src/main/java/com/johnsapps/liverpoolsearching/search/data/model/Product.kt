package com.johnsapps.liverpoolsearching.search.data.model

data class Product (
    val productId:String,
    val skuRepositoryId:String,
    val productDisplayName:String,
    val productType:String,
    val productRatingCount:Double,
    val productAvgRating:Double,
    val listPrice:Double,
    val minimumListPrice:Double,
    val maximumListPrice:Double,
    val promoPrice:Double,
    val minimumPromoPrice:Double,
    val maximumPromoPrice:Double,
    val isHybrid:Boolean,
    val isMarketPlace:Boolean,
    val isImportationProduct:Boolean,
    val brand:String,
    val seller:String,
    val category:String,
    val dwPromotionInfo:PromotionInfo,
    val smImage:String,
    val lgImage:String,
    val xlImage:String,
    val groupType:String,
    val plpFlags:Map<String,String>,
    val variantsColor:ArrayList<VariantsColor>
)

data class PromotionInfo(
    val dwToolTipInfo:String,
    val dWPromoDescription:String
)

data class VariantsColor(
    val colorName:String,
    val colorHex:String,
    val colorImageURL:String
)
