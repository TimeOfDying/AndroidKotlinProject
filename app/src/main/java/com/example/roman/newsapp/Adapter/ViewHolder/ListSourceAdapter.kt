package com.example.roman.newsapp.Adapter.ViewHolder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roman.newsapp.Interface.ItemClickListener
import com.example.roman.newsapp.ListNews
import com.example.roman.newsapp.Model.WebSite
import com.example.roman.newsapp.R
import kotlinx.android.synthetic.main.source_news_layout.view.*


class ListSourceAdapter(private val context: Context, private val webSite: WebSite): RecyclerView.Adapter<ListSourceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val itemView = inflater.inflate(R.layout.source_news_layout,parent, false)
        return ListSourceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return webSite.sources!!.size
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder?, position: Int) {
        holder!!.source_title.text = webSite.sources!![position].name


        holder.setItemClickListener(object : ItemClickListener
        {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context, ListNews::class.java)
                intent.putExtra("source", webSite.sources!![position].id)
                context.startActivity(intent)
            }
        })
    }
}