package com.example.dscatalogmobile.service

import com.example.dscatalogmobile.model.PageImpl
import com.example.dscatalogmobile.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ProdutoService {

    @GET("products")
    fun findAll(): Call<PageImpl<Produto>>

    @POST("products")
    fun adiciona(@Body produto: Produto?): Call<Produto?>?

    @GET("products/{produtoId}")
    fun findById(@Path("produtoId") produtoId: Long): Call<Produto?>?

}
