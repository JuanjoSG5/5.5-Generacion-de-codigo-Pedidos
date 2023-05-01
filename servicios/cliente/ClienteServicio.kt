package servicios.cliente

import cliente.Cliente
import pedidos.Pedidos

interface ClienteServicio {
    fun createCliente(cliente: Cliente): Int?
    fun deleteClienteByDni(dni: String): Int?
    fun updateCliente(cliente: Cliente): Int?
    fun getByDni(dni: String): Cliente?

    fun createPedido(pedido: Pedidos): Int?
    fun deletePedidoById(id: Int): Int?
    fun updatePedido(pedido: Pedidos): Int?

    fun getByID(id: Int):Pedidos?
}