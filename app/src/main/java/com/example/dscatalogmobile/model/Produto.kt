package com.example.dscatalogmobile.model

import java.util.*

class Produto(
    var id: Long?,
    var name: String?,
    var description: String?,
    var price: Double?,
    var imgUrl: String?,
    var date: Date?
) {
    constructor(produto: Produto?) : this(
        id = produto?.id,
        name = produto?.name,
        description = produto?.description,
        price = produto?.price,
        imgUrl = produto?.imgUrl,
        date = produto?.date
    ) {}

}
