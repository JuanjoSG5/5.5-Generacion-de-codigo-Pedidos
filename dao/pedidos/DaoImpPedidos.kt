package dao.pedidos

import pedidos.EstadoPedido
import pedidos.Pedidos
import java.sql.SQLException
import java.sql.Timestamp
import javax.sql.DataSource

class DaoImpPedidos(private val dataSource: DataSource):DaoPedidos {
    override fun createPedido(pedido: Pedidos): Int? {
        val sql = "INSERT INTO Pedidos (id,dni,estado,fechaRealizacion) " +
                "VALUES (?, ?, ?, ?, ?)"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, pedido.id.toString())
                    stmt.setString(2, pedido.dni)
                    stmt.setString(3, pedido.estado.name)
                    stmt.setString(3, pedido.fechaRealizacion.toString())
                    return stmt.executeUpdate()
                }
            }
        }catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun getById(id: Int): Pedidos? {
        val sql = "SELECT * FROM Pedidos WHERE id = ?"
        var pedido: Pedidos? = null
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        pedido = Pedidos(
                        id = rs.getInt("id"),
                        dni = rs.getString("dni"),
                        estado = EstadoPedido.valueOf(rs.getString("estado")),
                        fechaRealizacion = rs.getTimestamp("fechaRealizacion"))
                    }
                }
            }
            return pedido
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }


    override fun createTable() {
        val sql =
            "CREATE TABLE Pedidos (" +
                    "id NUMBER(4) PRIMARY KEY," +
                    "dni VARCHAR2(9)," +
                    "estado VARCHAR2(20)," +
                    "fechaRealizacion TIMESTAMP);"
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


    override fun deletePedidoById(id: Int): Int? {
        val sql = "DELETE FROM Pedidos WHERE id = ?"
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
    override fun updatePedido(pedido: Pedidos): Int? {
        val sql = "UPDATE Pedidos SET dni = ?, estado = ?, fechaRealizacion = ? WHERE id = ?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, pedido.dni)
                    stmt.setString(2, pedido.estado.name) // set estado using enum name
                    stmt.setTimestamp(3, Timestamp(pedido.fechaRealizacion.time))
                    stmt.setInt(4, pedido.id)
                    val rowsAffected = stmt.executeUpdate()
                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }



    override fun deleteTable() {
        val sql ="DROP TABLE Clientes;"
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


