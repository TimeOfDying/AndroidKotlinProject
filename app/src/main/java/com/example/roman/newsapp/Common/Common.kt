package com.example.roman.newsapp.Common

import com.example.roman.newsapp.Interface.NewsService
import com.example.roman.newsapp.Remote.RetrofitClient
import retrofit2.Retrofit

object Common {
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "a746b47fc0784f21ba96743a4010039d"

    val newService:NewsService
    get()= RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)

    fun getNewsAPI(source:String):String
    {
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?sources=")
                .append(source)
                .append("&apiKey=")
                .append(API_KEY)
                .toString()
        return apiUrl
    }
}