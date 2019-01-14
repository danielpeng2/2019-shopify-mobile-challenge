package ca.danielpeng.shopifychallenge.view

import android.support.annotation.StringRes
import ca.danielpeng.shopifychallenge.model.Product

interface DetailsView {

    fun showProducts(products: ArrayList<Product>)
    fun showError(@StringRes errorMessage: Int)
}