package ca.danielpeng.shopifychallenge.presenter

import ca.danielpeng.shopifychallenge.model.Collection

interface CollectionsListener {

    fun onCollectionClicked(collection: Collection)
}