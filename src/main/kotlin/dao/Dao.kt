package dao

import producto.Product

interface Dao {
    fun createProduct(product: Product): Int
    fun getById(id: Int): Product?
    fun createTable()
    fun deleteTable()
}