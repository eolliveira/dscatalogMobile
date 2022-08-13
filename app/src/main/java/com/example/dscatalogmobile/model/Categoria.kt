package com.example.dscatalogmobile.model

class Categoria(val id: Long?, val name: String?) {
    constructor(categoria: Categoria?) : this(
        categoria?.id,
        categoria?.name
    )
}