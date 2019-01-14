package ca.danielpeng.shopifychallenge.model

interface ShopifyInteractable {

    fun getCollections(responseListener: ResponseListener<ArrayList<Collection>>)

    fun getProducts(collectionId: String, responseListener: ResponseListener<ArrayList<Product>>)
}