package com.asiri.lbctest.repository

import com.asiri.lbctest.App
import com.asiri.lbctest.model.Title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.Executors


class TitleRepository {

    private val titleUrl = "https://static.leboncoin.fr/img/shared/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(titleUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Function for title http call
    fun syncTitleNow() {

        //Prepare and execute the service
        val service = retrofit.create(TitleApi::class.java)
        service.getTitles().enqueue(object : Callback<List<Title>> {
            override fun onResponse(call: Call<List<Title>>, response: Response<List<Title>>) {
                Timber.d("We got a response from the title api")
                val titles = response.body()
                if (titles != null) {
                    //Inserting all titles
                    Timber.d("%d titles were found", titles.size)
                    Executors.newSingleThreadExecutor().execute{
                        App.db.titleDao().insertTitles(titles)
                        Timber.d("titles inserted into database")
                    }
                }
            }

            override fun onFailure(call: Call<List<Title>>, t: Throwable) {
                Timber.e("HTTP GET fail")
                Timber.e(t)
            }
        })
        Timber.d("Executing an title HTTP GET")
    }
}