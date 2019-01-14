package ca.danielpeng.shopifychallenge.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.ShopifyInteractor
import ca.danielpeng.shopifychallenge.presenter.CollectionsPresenter
import kotlinx.android.synthetic.main.activity_collections.*

class CollectionsActivity : AppCompatActivity(), CollectionsView {

    private lateinit var presenter: CollectionsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val TAG_COLLECTION = "collection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        setTitle(R.string.title_collections)

        viewManager = LinearLayoutManager(this)
        list_collections.apply { layoutManager = viewManager }

        presenter = CollectionsPresenter(this, ShopifyInteractor)
        presenter.onCreate()
    }

    override fun showCollections(collections: ArrayList<Collection>) {
        viewAdapter = CollectionsAdapter(this, collections, presenter)
        runOnUiThread {
            list_collections.apply { adapter = viewAdapter }
        }
    }

    override fun showError(@StringRes errorMessage: Int) {
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun goToDetails(collection: Collection) {
        val newIntent = Intent(this, DetailsActivity::class.java)
        newIntent.putExtra(TAG_COLLECTION, collection)
        startActivity(newIntent)
    }
}
