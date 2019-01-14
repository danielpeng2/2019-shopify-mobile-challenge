package ca.danielpeng.shopifychallenge.presenter

import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.ResponseListener
import ca.danielpeng.shopifychallenge.model.ShopifyInteractable
import ca.danielpeng.shopifychallenge.view.CollectionsView
import java.lang.Exception

class CollectionsPresenter(val view: CollectionsView, private val interactor: ShopifyInteractable):
    Presenter, CollectionsListener {

    override fun onCreate() {
        interactor.getCollections(object : ResponseListener<ArrayList<Collection>> {
            override fun onFailure(e: Exception) {
                view.showError(R.string.error_network)
            }
            override fun onResponse(results: ArrayList<Collection>) {
                view.showCollections(results)
            }
        })
    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onDestroy() {}

    override fun onCollectionClicked(id: String) {
        // TODO
    }
}