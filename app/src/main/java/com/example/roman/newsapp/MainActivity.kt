package com.example.roman.newsapp

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.telecom.Call
import android.widget.Toast
import com.example.roman.newsapp.Adapter.ViewHolder.ListSourceAdapter
import com.example.roman.newsapp.Common.Common
import com.example.roman.newsapp.Interface.NewsService
import com.example.roman.newsapp.Model.WebSite
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mService: NewsService
    lateinit var adapter: ListSourceAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)

        mService = Common.newService

        swipe_to_refresh.setOnRefreshListener {
            loadWebSiteSource(true)
            
        }

        recycler_view_news_source.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler_view_news_source.layoutManager = layoutManager

        dialog = SpotsDialog(this)

        loadWebSiteSource(false)
    }

    private fun loadWebSiteSource(isRefresh: Boolean) {
        if(!isRefresh)
        {
            val cache = Paper.book().read<String>("cache")
            if(cache != null && !cache.isBlank() && cache != "null")
            {
                val webSite = Gson().fromJson<WebSite>(cache, WebSite::class.java)
                adapter = ListSourceAdapter(baseContext,webSite)
                adapter.notifyDataSetChanged()
                recycler_view_news_source.adapter = adapter
            }
            else
            {
                dialog.show()
                mService.sources.enqueue(object : retrofit2.Callback<WebSite>
                {
                    override fun onFailure(call: retrofit2.Call<WebSite>?, t: Throwable?) {
                        Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: retrofit2.Call<WebSite>?, response: Response<WebSite>?) {
                        adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                        adapter.notifyDataSetChanged()
                        recycler_view_news_source.adapter = adapter

                        Paper.book().write("cache", Gson().toJson(response!!.body()!!))
                        dialog.dismiss()
                    }
                })
            }
        }
        else
        {
            swipe_to_refresh.isRefreshing=true
            mService.sources.enqueue(object : retrofit2.Callback<WebSite>
            {
                override fun onFailure(call: retrofit2.Call<WebSite>?, t: Throwable?) {
                    Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: retrofit2.Call<WebSite>?, response: Response<WebSite>?) {
                    adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                    adapter.notifyDataSetChanged()
                    recycler_view_news_source.adapter = adapter

                    Paper.book().write("cache", Gson().toJson(response!!.body()!!))
                    swipe_to_refresh.isRefreshing=false
                }
            })
        }
    }

}
