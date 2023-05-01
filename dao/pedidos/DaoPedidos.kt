package dao.pedidos

import dao.Dao
import pedidos.Pedidos

interface DaoPedidos: Dao {
    fun createPedido(pedido: Pedidos): Int?
    fun deletePedidoById(id: Int): Int?
    fun updatePedido(pedido: Pedidos): Int?
    fun getById(id: Int): Pedidos?
}