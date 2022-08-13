package com.example.dscatalogmobile.Retrofit

import com.example.dscatalogmobile.service.ProdutoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.109:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var produtoService = retrofit.create(ProdutoService::class.java)



}