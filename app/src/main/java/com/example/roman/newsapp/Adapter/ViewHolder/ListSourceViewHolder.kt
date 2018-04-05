package com.example.roman.newsapp.Adapter.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.roman.newsapp.Interface.ItemClickListener
import kotlinx.android.synthetic.main.source_news_layout.view.*


class ListSourceViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private lateinit var itemClickListener: ItemClickListener

    init {
       itemView.setOnClickListener(this)
    }

    var source_title = itemView.source_title

    fun setItemClickListener(itemClickListener: ItemClickListener)
    {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!,adapterPosition)
    }


}