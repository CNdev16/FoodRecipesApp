package com.example.allrecipesfree_foodrecipesapp.utility.epoxy.controller

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.allrecipesfree_foodrecipesapp.utility.epoxy.HeaderLabelEpoxyModel_
import com.example.allrecipesfree_foodrecipesapp.utility.epoxy.ItemsRecipesEpoxyModel_
import com.example.core.data.CountryCategory
import kotlin.properties.Delegates

class ItemsController : EpoxyController() {
    init {
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }

    var itemsRecipe by Delegates.observable(emptyList<CountryCategory>()) { _, _, _ ->
        requestModelBuild()
    }

    override fun buildModels() {
        HeaderLabelEpoxyModel_()
            .id("HEADER_Favorite")
            .header("My Favorite Recipes")
            .addTo(this)

        val item = itemsRecipe.map {
            ItemsRecipesEpoxyModel_()
                .id("ITEMS")
        }

        CarouselModel_()
            .id("HORIZONTAL_FAV_ITEMS")
            .numViewsToShowOnScreen(2.2f)
            .models(item)
            .addIf(itemsRecipe.isNotEmpty(), this)

        HeaderLabelEpoxyModel_()
            .id("HEADER_LAST_WATCHED")
            .header("Last Watched Recipes")
            .addTo(this)

        CarouselModel_()
            .id("HORIZONTAL_LAST_ITEMS")
            .numViewsToShowOnScreen(2.2f)
            .models(item)
            .addIf(itemsRecipe.isNotEmpty(), this)

        HeaderLabelEpoxyModel_()
            .id("HEADER_REC")
            .header("Recommended Recipes")
            .addTo(this)

        CarouselModel_()
            .id("HORIZONTAL_REC_ITEMS")
            .numViewsToShowOnScreen(2.2f)
            .models(item)
            .addIf(itemsRecipe.isNotEmpty(), this)
    }

}