package ca.danielpeng.shopifychallenge.view

import android.support.annotation.StringRes
import ca.danielpeng.shopifychallenge.model.Collection

interface CollectionsView {

    fun showCollections(collections: ArrayList<Collection>)
    fun showError(@StringRes errorMessage: Int)
    fun goToDetails(collection: Collection)
}