package servicios

import producto.Product

interface ProductService {
    fun createProduct(product: Product): Int

    fun getById(id: Int): Product?

    fun createTable()
}
