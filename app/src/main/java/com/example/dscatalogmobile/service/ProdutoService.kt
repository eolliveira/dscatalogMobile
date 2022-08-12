package com.example.dscatalogmobile.service

import com.example.dscatalogmobile.model.Produto
import retrofit2.Call
import retrofit2.http.GET


interface ProdutoService {

    @GET("products/list")
    fun findAll(): Call<List<Produto>>

}
