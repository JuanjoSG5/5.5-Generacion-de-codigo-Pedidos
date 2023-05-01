package dao.sistema

import cliente.Cliente
import producto.Producto
import sistema.Sistema

interface DaoSistema {
    fun createSistema(sistema: Sistema): Int?

     fun getByDni(dni: String): Cliente?
    fun getById(id: Int): Producto?
}