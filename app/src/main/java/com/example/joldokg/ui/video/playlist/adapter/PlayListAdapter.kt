package com.example.joldokg.ui.video.playlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.joldokg.R
import com.example.joldokg.`interface`.OnItemClickListener
import com.example.joldokg.data.models.PlaylistItems
import com.example.joldokg.extensions.loadImage


class PlayListAdapter (var onItemClickListener: OnItemClickListener)
    :RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>(){

    var listUrl : MutableList<PlaylistItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_holder,parent,false)
        return PlayListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUrl.count()
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.onBind(listUrl[position])
    }
    fun addItems(item: MutableList<PlaylistItems>) {
        listUrl.clear()
        listUrl.addAll(item)
        notifyDataSetChanged()
    }

    inner class PlayListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image_holder)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val amountSeries: TextView = itemView.findViewById(R.id.tv_amount_series)

        fun onBind(playlistItems: PlaylistItems){
            image.loadImage(playlistItems.snippet?.thumbnails?.medium?.url.toString())
            title.text = playlistItems.snippet?.title
            amountSeries.text = playlistItems.contentDetails?.itemCount
            itemView.setOnClickListener {
                onItemClickListener.itemClick(adapterPosition)
            }

        }


    }
}