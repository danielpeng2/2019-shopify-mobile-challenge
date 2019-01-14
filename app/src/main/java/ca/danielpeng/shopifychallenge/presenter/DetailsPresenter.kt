package ca.danielpeng.shopifychallenge.presenter

import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.ShopifyInteractable
import ca.danielpeng.shopifychallenge.view.DetailsView

class DetailsPresenter(val view: DetailsView,
                       private val interactor: ShopifyInteractable,
                       val collection: Collection) : Presenter {

    override fun onCreate() {

    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onDestroy() {}
}