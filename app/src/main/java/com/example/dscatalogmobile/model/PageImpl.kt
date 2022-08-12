package com.example.dscatalogmobile.model

class PageImpl<T> {

    private var content: List<T?>? = null
    private var last = false
    private var totalPages = 0f
    private var totalElements = 0f
    private var size = 0f
    private var number = 0f
    private var first = false
    private var numberOfElements = 0f

    fun getTotalPages(): Float {
        return totalPages
    }

    fun setTotalPages(totalPages: Float) {
        this.totalPages = totalPages
    }

    fun getTotalElements(): Float {
        return totalElements
    }

    fun setTotalElements(totalElements: Float) {
        this.totalElements = totalElements
    }

    fun getSize(): Float {
        return size
    }

    fun setSize(size: Float) {
        this.size = size
    }

    // Setter Methods

    // Setter Methods
    fun getNumber(): Float {
        return number
    }

    fun setNumber(number: Float) {
        this.number = number
    }

    fun getNumberOfElements(): Float {
        return numberOfElements
    }

    fun setNumberOfElements(numberOfElements: Float) {
        this.numberOfElements = numberOfElements
    }

    fun getContent(): List<T?>? {
        return content
    }

    fun setContent(content: List<T?>?) {
        this.content = content
    }

    fun isLast(): Boolean {
        return last
    }

    fun setLast(last: Boolean) {
        this.last = last
    }

    fun isFirst(): Boolean {
        return first
    }

    fun setFirst(first: Boolean) {
        this.first = first
    }
}