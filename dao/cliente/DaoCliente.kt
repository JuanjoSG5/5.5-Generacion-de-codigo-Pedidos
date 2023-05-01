package dao.cliente

import cliente.Cliente
import dao.Dao


interface DaoCliente: Dao {
    fun createCliente(cliente: Cliente): Int?
    fun deleteClienteByDni(dni: String): Int?
    fun updateCliente(cliente: Cliente): Int?


}