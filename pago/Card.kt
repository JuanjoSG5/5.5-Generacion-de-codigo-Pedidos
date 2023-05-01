package pago

import java.util.*

class Card: FormaPago {
    private var numeroTarjeta = 0
    private var fecCad = Date()
    private var tipoTarjeta = TipoTarjeta.Credito

}