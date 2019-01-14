package ca.danielpeng.shopifychallenge.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.Toast
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.ShopifyInteractor
import ca.danielpeng.shopifychallenge.presenter.CollectionsPresenter

class CollectionsActivity : AppCompatActivity(), CollectionsView {

    private lateinit var presenter: CollectionsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        setTitle(R.string.title_collections)

        presenter = CollectionsPresenter(this, ShopifyInteractor)
        presenter.onCreate()
    }

    override fun showError(@StringRes errorMessage: Int) {
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}
