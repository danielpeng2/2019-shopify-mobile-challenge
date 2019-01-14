package ca.danielpeng.shopifychallenge.presenter

import android.util.Log
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.ResponseListener
import ca.danielpeng.shopifychallenge.model.ShopifyInteractable
import ca.danielpeng.shopifychallenge.view.CollectionsView
import java.lang.Exception

class CollectionsPresenter(val view: CollectionsView, private val interactor: ShopifyInteractable): Presenter {

    override fun onCreate() {
        interactor.getCollections(object : ResponseListener<ArrayList<Collection>> {
            override fun onFailure(e: Exception) {

            }

            override fun onResponse(results: ArrayList<Collection>) {
                Log.d("wf", "dsfs")
            }
        })
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }
}