import dao.cliente.DaoImpCliente
import dao.producto.DaoImpProducto
import factoriaDatos.DataFactory
import producto.Producto
import servicios.producto.ProductServImpl

fun main() {
    val dataSource = DataFactory.getDataSource(DataFactory.DataSourceType.Embedded)
    val daoProducto = DaoImpProducto(dataSource)
    val daoCliente =DaoImpCliente(dataSource)
    val userServiceImpl = ProductServImpl(daoProducto)
    userServiceImpl.createTable()
    val product = Producto(1, "Smartphone", 999.99f, "The latest smartphone model",
        "Apple", "Electronics")
    val createResult = userServiceImpl.createProduct(product)
    println(createResult)

    val productId = userServiceImpl.getById(1)?.id
    println("Product ID: $productId")
}