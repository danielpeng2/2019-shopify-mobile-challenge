package ca.danielpeng.shopifychallenge.presenter

import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.Product
import ca.danielpeng.shopifychallenge.model.ResponseListener
import ca.danielpeng.shopifychallenge.model.ShopifyInteractable
import ca.danielpeng.shopifychallenge.view.DetailsView
import java.lang.Exception

class DetailsPresenter(val view: DetailsView,
                       private val interactor: ShopifyInteractable,
                       val collection: Collection) : Presenter {

    override fun onCreate() {
        interactor.getProducts(collection.id, object : ResponseListener<ArrayList<Product>> {
            override fun onFailure(e: Exception) {
                view.showError(R.string.error_network)
            }

            override fun onResponse(results: ArrayList<Product>) {

            }
        })
    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onDestroy() {}
}