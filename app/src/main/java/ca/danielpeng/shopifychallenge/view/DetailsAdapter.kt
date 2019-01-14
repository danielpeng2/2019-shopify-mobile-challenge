package ca.danielpeng.shopifychallenge.view

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class DetailsAdapter(val context: Activity, private val products: ArrayList<Product>):
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var productNameView: TextView = view.text_product_name
        var inventoryView: TextView = view.text_inventory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productNameView.text = products[position].name
        holder.inventoryView.text = products[position].count.toString()
    }

    override fun getItemCount() = products.size
}