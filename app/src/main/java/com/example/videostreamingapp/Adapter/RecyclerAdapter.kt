package com.example.videostreamingapp.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.videostreamingapp.Model.PlayerActivity
import com.example.videostreamingapp.Model.Video
import com.example.videostreamingapp.R
import com.squareup.picasso.Picasso

class RecyclerAdapter(val list:List<Video>,val context: Context): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val videoThumbnail= itemView.findViewById<ImageView>(R.id.videoThumbnail)
        val videoTitle = itemView.findViewById<TextView>(R.id.videoTitle)
        val videoSrc=  itemView.findViewById<TextView>(R.id.videoSrc)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.video_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(list[position].thumb).into(holder.videoThumbnail)
        holder.videoTitle.text=list[position].title
        holder.videoSrc.text=list[position].subtitle
        holder.cardView.setOnClickListener {
            val intent= Intent(context,PlayerActivity::class.java)
            val b=Bundle()
            b.putSerializable("videos",list[position])
            intent.putExtras(b)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}