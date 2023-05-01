package pedidos

import producto.Producto
import java.sql.Timestamp
import java.util.*

class Pedidos( var id :Int,
              var dni: String,
               var estado : EstadoPedido,
               var fechaRealizacion : Timestamp)
