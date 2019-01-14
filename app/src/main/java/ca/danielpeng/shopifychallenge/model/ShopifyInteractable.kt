package ca.danielpeng.shopifychallenge.model

interface ShopifyInteractable {

    fun getCollections(responseListener: ResponseListener<ArrayList<Collection>>)
}