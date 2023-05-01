package servicios.producto

import dao.producto.DaoProducto
import producto.Producto

class ProductServImpl(private val dao: DaoProducto) : ProductService {
    override fun createProduct(product: Producto):Int? {
        return dao.createProduct(product)
    }

    override fun deleteProduct(product: Producto):Int? {
        return dao.deleteProductById(product.id)
    }

    override fun updateProduct(product: Producto): Int? {
        return dao.updateProduct(product)
    }

    override fun getById(id: Int):Producto?{
        return dao.getById(id)
    }

    override fun createTable(){
        dao.createTable()
    }
}