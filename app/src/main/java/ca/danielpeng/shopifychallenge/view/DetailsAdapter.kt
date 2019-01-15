package ca.danielpeng.shopifychallenge.view

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Collection
import ca.danielpeng.shopifychallenge.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class DetailsAdapter(
    private val context: Activity,
    private val products: ArrayList<Product>,
    private val collection: Collection
) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var productNameView: TextView = view.text_product_name
        var inventoryView: TextView = view.text_inventory
        var colImageView: ImageView = view.image_collection
        var collectionNameView: TextView = view.text_collection_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        val inventoryCount = product.count.toString()

        holder.productNameView.text = product.name
        holder.inventoryView.text = context.resources.getString(R.string.text_inventory, inventoryCount)
        holder.collectionNameView.text = collection.name
        Glide.with(context)
            .load(collection.imageUrl)
            .into(holder.colImageView)
    }

    override fun getItemCount() = products.size
}