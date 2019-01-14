package ca.danielpeng.shopifychallenge.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.ShopifyInteractor
import ca.danielpeng.shopifychallenge.presenter.DetailsPresenter

class DetailsActivity : AppCompatActivity(), DetailsView {

    private lateinit var presenter: DetailsPresenter

    private val TAG_COLLECTION = "collection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val collection = intent.getSerializableExtra(TAG_COLLECTION) as Collection
        presenter = DetailsPresenter(this, ShopifyInteractor, collection)
        presenter.onCreate()
    }
}