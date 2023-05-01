package servicios.cliente

import cliente.Cliente
import dao.cliente.DaoCliente
import dao.pedidos.DaoPedidos
import pedidos.Pedidos

class ClienteServImpl(private val daoCliente : DaoCliente, private val daoPedidos: DaoPedidos):ClienteServicio {
    override fun createCliente(cliente: Cliente): Int? {
        return daoCliente.createCliente(cliente)
    }

    override fun deleteClienteByDni(dni: String): Int? {
        return daoCliente.deleteClienteByDni(dni)
    }

    override fun updateCliente(cliente: Cliente): Int? {
        return daoCliente.updateCliente(cliente)
    }

    override fun getByDni(dni: String): Cliente? {
        return daoCliente.getByDni(dni)
    }

    override fun createPedido(pedido: Pedidos): Int? {
        return daoPedidos.createPedido(pedido)
    }

    override fun deletePedidoById(id: Int): Int? {
        return daoPedidos.deletePedidoById(id)
    }

    override fun updatePedido(pedido: Pedidos): Int? {
        return daoPedidos.updatePedido(pedido)
    }

    override fun getByID(id: Int): Pedidos? {
        return daoPedidos.getById(id)
    }
}