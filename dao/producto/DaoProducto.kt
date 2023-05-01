package dao.producto

import dao.Dao
import producto.Producto

interface DaoProducto: Dao {
    fun createProduct(product: Producto): Int?
    fun deleteProductById(id: Int): Int?
    fun updateProduct(product: Producto): Int?
    fun getById(id: Int): Producto?

}