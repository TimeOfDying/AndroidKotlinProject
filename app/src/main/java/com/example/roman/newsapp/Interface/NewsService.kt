package com.example.roman.newsapp.Interface

import com.example.roman.newsapp.Model.News
import com.example.roman.newsapp.Model.WebSite
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface NewsService {

    @get:GET("v2/sources?apiKey=a746b47fc0784f21ba96743a4010039d")
    val sources: Call<WebSite>

    @GET
    fun getNewsFromSource(@Url url: String ): Call<News>
}