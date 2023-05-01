package servicios.producto

import producto.Producto

interface ProductService {
    fun createProduct(product: Producto): Int?

    fun deleteProduct(product: Producto): Int?

    fun updateProduct(product: Producto): Int?

    fun getById(id: Int): Producto?

    fun createTable()
}
