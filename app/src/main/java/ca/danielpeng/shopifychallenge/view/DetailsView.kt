package ca.danielpeng.shopifychallenge.view

import android.support.annotation.StringRes
import ca.danielpeng.shopifychallenge.model.Product

interface DetailsView {

    fun setTitle(title: String)
    fun setDetails(imageUrl: String, name: String, body: String)
    fun hideDetailsBody()
    fun showProducts(products: ArrayList<Product>)
    fun showError(@StringRes errorMessage: Int)
}