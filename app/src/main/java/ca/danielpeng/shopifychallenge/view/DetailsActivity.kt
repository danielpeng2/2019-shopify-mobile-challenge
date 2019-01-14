package ca.danielpeng.shopifychallenge.view

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.Product
import ca.danielpeng.shopifychallenge.model.ShopifyInteractor
import ca.danielpeng.shopifychallenge.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsView {

    private lateinit var presenter: DetailsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val TAG_COLLECTION = "collection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewManager = LinearLayoutManager(this)
        list_products.apply { layoutManager = viewManager }

        val collection = intent.getSerializableExtra(TAG_COLLECTION) as Collection
        presenter = DetailsPresenter(this, ShopifyInteractor, collection)
        presenter.onCreate()
    }

    override fun showProducts(products: ArrayList<Product>) {
        viewAdapter = DetailsAdapter(this, products)
        runOnUiThread {
            list_products.apply { adapter = viewAdapter }
        }
    }

    override fun showError(@StringRes errorMessage: Int) {
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}