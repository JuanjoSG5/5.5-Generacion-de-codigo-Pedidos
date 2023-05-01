package producto

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Float,
    val descripcion: String,
    val impuestos: String,
    val cantidad: String,
    val pedidoId: Int
)

