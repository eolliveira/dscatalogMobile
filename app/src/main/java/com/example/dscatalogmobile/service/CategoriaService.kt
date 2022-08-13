package com.example.dscatalogmobile.service

import com.example.dscatalogmobile.model.Categoria
import com.example.dscatalogmobile.model.PageImpl
import retrofit2.Call
import retrofit2.http.GET

interface CategoriaService {
    @GET("categories")
    fun findAll(): Call<PageImpl<Categoria>>
}