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
import ca.danielpeng.shopifychallenge.presenter.CollectionsListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_collection.view.*

class CollectionsAdapter(val context: Activity,
                         private val collections: ArrayList<Collection>,
                         private val collectionsListener: CollectionsListener):
        RecyclerView.Adapter<CollectionsAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var colImageView: ImageView = view.image_collection
        var nameView: TextView = view.name_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_collection, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = collections[position].name
        holder.view.setOnClickListener {
            collectionsListener.onCollectionClicked(collections[position])
        }
        Glide.with(context)
            .load(collections[position].imageUrl)
            .into(holder.colImageView)
    }

    override fun getItemCount() = collections.size
}