package servicios

import dao.Dao
import producto.Product

class ProductServImpl(private val dao: Dao) : ProductService {
    override fun createProduct(product: Product):Int {
        return dao.createProduct(product)
    }

    override fun getById(id: Int):Product?{
        return dao.getById(id)
    }

    override fun createTable(){
        dao.createTable()
    }
}