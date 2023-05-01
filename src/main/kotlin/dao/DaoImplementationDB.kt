package dao

import producto.Product
import javax.sql.DataSource

class DaoImplementationDB(private val dataSource: DataSource): Dao {
    override fun createProduct(product: Product): Int {
        val sql = "INSERT INTO productos (id, nombre, precio, descripcion, marca, categoria) VALUES (?, ?, ?, ?, ?, ?)"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, product.id.toString())
                stmt.setString(2, product.nombre)
                stmt.setString(3, product.precio.toString())
                stmt.setString(4, product.descripcion)
                stmt.setString(5, product.marca)
                stmt.setString(6, product.categoria)

                return stmt.executeUpdate()
            }
        }
    }

    override fun getById(id: Int): Product? {
        val sql = "SELECT * FROM productos WHERE ID = ?"
        var product: Product? = null
        dataSource.connection.use {conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                    if (rs.next()) {
                        product = Product(
                            id = rs.getInt("id"),
                            nombre = rs.getString("nombre"),
                            precio = rs.getFloat("precio"),
                            descripcion = rs.getString("descripcion"),
                            marca = rs.getString("marca"),
                            categoria = rs.getString("categoria")
                        )
                    }else {
                        null
                    }
                }
            }
            return product
        }

    override fun createTable() {
        val sql =
            "CREATE TABLE productos (" +
                    "id NUMBER(4) PRIMARY KEY," +
                    "nombre VARCHAR2(50), " +
                    "precio NUMBER(12,4), " +
                    "descripcion VARCHAR2(100), " +
                    "marca VARCHAR2(50), " +
                    "categoria VARCHAR2(50));"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.executeUpdate()
            }
        }
    }
    override fun deleteTable() {
        val sql ="DROP TABLE productos CASCADE  CONSTRAINTS;"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.executeUpdate()
            }
        }
    }
}
