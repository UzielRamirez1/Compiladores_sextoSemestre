typealias Estado = String
typealias Transicion = (Estado, Char) -> Estado?

fun main() {

    val estadoInicial: Estado = "A"
    val estadosFinales = setOf("E", "J")

    val transicion: Transicion = { estado, simbolo ->
        when (estado) {
            "A" -> when (simbolo) {
                '0' -> "B"
                '1' -> "C"
                else -> null
            }
            "B" -> when (simbolo) {
                '0' -> "D"
                '1' -> "E"
                else -> null
            }
            "C" -> when (simbolo) {
                '0' -> "F"
                '1' -> "G"
                else -> null
            }
            "D" -> when (simbolo) {
                '0' -> "D"
                '1' -> "H"
                else -> null
            }
            "E" -> when (simbolo) {
                '0' -> "F"
                '1' -> "I"
                else -> null
            }
            "F" -> when (simbolo) {
                '0' -> "I"
                '1' -> "J"
                else -> null
            }
            "G" -> when (simbolo) {
                '0' -> "F"
                '1' -> "G"
                else -> null
            }
            "H" -> when (simbolo) {
                '0' -> "F"
                '1' -> "I"
                else -> null
            }
            "I" -> when (simbolo) {
                '0', '1' -> "I"
                else -> null
            }
            "J" -> when (simbolo) {
                '0', '1' -> "I"
                else -> null
            }
            else -> null
        }
    }

    fun procesar(cadena: String): Boolean =
        cadena
            .fold(estadoInicial as Estado?) { estadoActual, simbolo ->
                estadoActual?.let { transicion(it, simbolo) }
            }
            ?.let { it in estadosFinales } ?: false

    val cadena = "0000000000000000101"
    println("cadena = $cadena \n" + if (procesar(cadena)) "Cadena aceptada" else "Cadena rechazada")
}