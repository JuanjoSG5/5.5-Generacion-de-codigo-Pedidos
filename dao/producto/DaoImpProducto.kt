package dao.producto

import producto.Producto
import java.sql.SQLException
import javax.sql.DataSource

class DaoImpProducto(private val dataSource: DataSource): DaoProducto {
    override fun createProduct(product: Producto): Int? {
        val sql = "INSERT INTO Producto (id, nombre, precio, descripcion, impuestos, cantidad) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
        try {


            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, product.id.toString())
                    stmt.setString(2, product.nombre)
                    stmt.setString(3, product.precio.toString())
                    stmt.setString(4, product.descripcion)
                    stmt.setString(5, product.impuestos)
                    stmt.setString(6, product.cantidad)

                    return stmt.executeUpdate()
                }
            }
        }catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun getById(id: Int): Producto? {
        val sql = "SELECT * FROM Producto WHERE ID = ?"
        var product: Producto? = null
        try {
            dataSource.connection.use {conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                        if (rs.next()) {
                            product = Producto(
                                id = rs.getInt("id"),
                                nombre = rs.getString("nombre"),
                                precio = rs.getFloat("precio"),
                                descripcion = rs.getString("descripcion"),
                                impuestos = rs.getString("impuestos"),
                                cantidad = rs.getString("cantidad"),
                                pedidoId = rs.getInt("pedidoId")
                            )
                        }else {
                            null
                        }
                    }
                }
            return product
        }catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun updateProduct(product: Producto): Int? {
        val sql = "UPDATE Producto SET nombre=?, precio=?, descripcion=?, impuestos=?, cantidad=? WHERE id=?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, product.nombre)
                    stmt.setString(2, product.precio.toString())
                    stmt.setString(3, product.descripcion)
                    stmt.setString(4, product.impuestos)
                    stmt.setString(5, product.cantidad)
                    stmt.setInt(6, product.id)

                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }


    override fun createTable() {
        val sql =
            "CREATE TABLE Producto (" +
                    "id NUMBER(4) PRIMARY KEY," +
                    "nombre VARCHAR2(50), " +
                    "precio NUMBER(12,4), " +
                    "descripcion VARCHAR2(100), " +
                    "impuestos VARCHAR2(50), " +
                    "cantidad VARCHAR2(50), " +
                    "pedidoId NUMBER(4)" +
                    "FOREIGN KEY (pedido_id) REFERENCES Pedidos(id));"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.executeUpdate()
                }
            }
        }catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    override fun deleteProductById(id: Int): Int? {
        val sql = "DELETE FROM Producto WHERE id = ?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }



    override fun deleteTable() {
        val sql ="DROP TABLE Producto;"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
}
