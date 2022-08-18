package com.example.dscatalogmobile.Retrofit

import com.example.dscatalogmobile.service.CategoriaService
import com.example.dscatalogmobile.service.ProdutoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.5.237:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var produtoService = retrofit.create(ProdutoService::class.java)
    var categoriaService = retrofit.create(CategoriaService::class.java)

}