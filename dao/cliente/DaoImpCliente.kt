package dao.cliente

import cliente.Cliente
import java.sql.SQLException
import javax.sql.DataSource


class DaoImpCliente(private val dataSource: DataSource): DaoCliente {
        override fun createCliente(cliente: Cliente): Int? {
            val sql = "INSERT INTO Clientes (dni, nombre) " +
                    "VALUES (?, ?)"
            try {
                dataSource.connection.use { conn ->
                    conn.prepareStatement(sql).use { stmt ->
                        stmt.setString(1, cliente.dni)
                        stmt.setString(2, cliente.nombre)
                        return stmt.executeUpdate()
                    }
                }
            }catch (ex: SQLException) {
                ex.printStackTrace()
                return null
            }
        }

    override fun getByDni(dni: String): Cliente? {
        val sql = "SELECT * FROM Clientes WHERE DNI = ?"
        var cliente: Cliente? = null
        try {
            dataSource.connection.use {conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, dni)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        cliente = Cliente(
                            dni = rs.getString("dni"),
                            nombre = rs.getString("nombre")
                        )
                    }
                }
            }
            return cliente
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }


    override fun createTable() {
            val sql =
                "CREATE TABLE Clientes (" +
                        "dni VARCHAR2(9) PRIMARY KEY," +
                        "nombre VARCHAR2(25));"
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

    override fun deleteClienteByDni(dni: String): Int? {
            val sql = "DELETE FROM Clientes WHERE dni = ?"
            try {
                dataSource.connection.use { conn ->
                    conn.prepareStatement(sql).use { stmt ->
                        stmt.setString(1, dni)
                        return stmt.executeUpdate()
                    }
                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
                return null
            }
        }

    override fun updateCliente(cliente: Cliente): Int? {
        val sql = "UPDATE Clientes SET nombre=? WHERE dni=?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, cliente.nombre)
                    stmt.setString(2, cliente.dni)

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

