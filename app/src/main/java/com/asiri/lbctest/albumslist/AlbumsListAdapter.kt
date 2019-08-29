package com.asiri.lbctest.albumslist

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.asiri.lbctest.R
import com.asiri.lbctest.model.Album
import com.squareup.picasso.Picasso

class AlbumsListAdapter(
    private val albums: List<Album>,
    private val listener: AlbumsListAdapterListener?
) : RecyclerView.Adapter<AlbumsListAdapter.ViewHolder>(), View.OnClickListener {

    interface AlbumsListAdapterListener {
        fun onAlbumSelected(album: Album)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumView = itemView.findViewById<ConstraintLayout>(R.id.album_view)!!
        val albumImage = itemView.findViewById<ImageView>(R.id.album_image)!!
        val albumTitle = itemView.findViewById<TextView>(R.id.album_title)!!
        val albumNbTitles = itemView.findViewById<TextView>(R.id.album_number_of_titles)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        with(holder) {
            albumView.setOnClickListener(this@AlbumsListAdapter)
            albumView.tag = album
            albumTitle.text = Resources.getSystem().getString(R.string.album_view_title, album.id)
            albumNbTitles.text = Resources.getSystem().getString(R.string.album_number_of_titles, album.nbOfTitles)

            Picasso.get()
                .load(album.thumbnailUrl)
                .placeholder(R.drawable.ic_default_album)
                .into(albumImage)
        }

    }

    override fun getItemCount() = albums.size

    override fun onClick(view: View) {
        when (view.id) {
            R.id.album_view -> listener?.onAlbumSelected(view.tag as Album)
        }
    }

}