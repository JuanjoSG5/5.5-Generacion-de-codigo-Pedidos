import dao.DaoImplementationDB
import factoriaDatos.DataFactory
import producto.Product
import servicios.ProductServImpl

fun main() {
    // La mayoria del codigo esta basado en el codigo de este enlace: https://github.com/revilofe/UserService
    // el codigo es practicamente el mismo con algunos cambios como acortar clases, adaptar a la BD H2 ...
    val dataSource = DataFactory.getDataSource(DataFactory.DataSourceType.Embedded)
    val daoBD = DaoImplementationDB(dataSource)
    val userServiceImpl = ProductServImpl(daoBD)
    userServiceImpl.createTable()
    val product = Product(1, "Smartphone", 999.99f, "The latest smartphone model",
        "Apple", "Electronics")
    val createResult = userServiceImpl.createProduct(product)
    println(createResult)

    val productId = userServiceImpl.getById(1)?.id
    println("Product ID: $productId")
}