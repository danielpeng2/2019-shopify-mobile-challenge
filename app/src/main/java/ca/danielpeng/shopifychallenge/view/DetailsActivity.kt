package ca.danielpeng.shopifychallenge.view

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.Product
import ca.danielpeng.shopifychallenge.model.ShopifyInteractor
import ca.danielpeng.shopifychallenge.presenter.DetailsPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsView {

    private lateinit var presenter: DetailsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var collection: Collection

    private val TAG_COLLECTION = "collection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewManager = LinearLayoutManager(this)
        list_products.apply { layoutManager = viewManager }

        collection = intent.getSerializableExtra(TAG_COLLECTION) as Collection
        presenter = DetailsPresenter(this, ShopifyInteractor, collection)
        presenter.onCreate()
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setDetails(imageUrl: String, name: String, body: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(image_details)
        text_details_title.text = name
        text_details_body.text = body
    }

    override fun hideDetailsBody() {
        text_details_body.visibility = View.GONE
    }

    override fun showProducts(products: ArrayList<Product>) {
        viewAdapter = DetailsAdapter(this, products, collection)
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