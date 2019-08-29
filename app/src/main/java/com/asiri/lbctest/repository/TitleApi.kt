package com.asiri.lbctest.repository

import com.asiri.lbctest.model.Title
import retrofit2.Call
import retrofit2.http.GET

interface TitleApi {

    @GET("technical-test.json")
    fun getTitles(): Call<List<Title>>
}