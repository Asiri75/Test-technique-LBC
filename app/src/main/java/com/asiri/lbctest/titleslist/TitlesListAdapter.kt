package com.asiri.lbctest.titleslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.asiri.lbctest.R
import com.asiri.lbctest.model.Title
import com.squareup.picasso.Picasso

class TitlesListAdapter(private val titles: List<Title>) : RecyclerView.Adapter<TitlesListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<ConstraintLayout>(R.id.title_view)!!
        val titleImage = itemView.findViewById<ImageView>(R.id.title_image)!!
        val titleTitle = itemView.findViewById<TextView>(R.id.title_title)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_title, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = titles[position]
        with(holder) {
            //Update the title elements
            titleView.tag = title
            titleTitle.text = title.title
            Picasso.get()
                .load(title.thumbnailUrl)
                .placeholder(R.drawable.ic_default_album)
                .into(titleImage)
        }

    }
    override fun getItemCount() = titles.size

}