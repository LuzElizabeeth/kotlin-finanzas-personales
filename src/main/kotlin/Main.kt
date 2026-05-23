data class Movimiento(
    val tipo: String,
    val categoria: String,
    val descripcion: String,
    val monto: Double
)

fun main() {
    val movimientos = mutableListOf<Movimiento>()
    var opcion: String?

    do {
        mostrarMenu()
        opcion = readLine()

        when (opcion) {
            "1" -> agregarMovimiento(movimientos, "Ingreso")
            "2" -> agregarMovimiento(movimientos, "Gasto")
            "3" -> mostrarMovimiento(movimientos)
            "4" -> mostrarSaldo(movimientos)
            "5" -> println("Filtrar gastos")
            "6" -> mostrarResumenPorCategoria(movimientos)
            "7" -> eliminarMovimiento(movimientos)
            "8" -> println("Saliendo del programa...")
            else -> println("Opción no válida.")
        }

    } while (opcion != "8")
}

fun mostrarMenu() {
    println("\n--- Control de Finanzas Personales ---")
    println("1. Agregar ingreso")
    println("2. Agregar gasto")
    println("3. Ver movimientos")
    println("4. Ver saldo")
    println("5. Filtrar gastos mayores a una cantidad")
    println("6. Ver resumen por categoría")
    println("7. Eliminar movimiento")
    println("8. Salir")
    print("Elige una opción: ")
}

fun agregarMovimiento(movimientos: MutableList<Movimiento>, tipo: String) {
    println("Categorías sugeridas:")
    println("Comida | Transporte | Escuela | Entretenimiento | Ahorro")

    print("Categoría: ")

    val categoria = readLine()
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: "General"

    val descripcion = readLine()
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: "Sin descripción"

    println("Error: el monto debe ser mayor a 0.")

    if (monto == null || monto <= 0) {
        println("Monto inválido.")
        return
    }

    movimientos.add(Movimiento(tipo, categoria, descripcion, monto))
    println("$tipo registrado correctamente.")
}

fun mostrarMovimientos(movimientos: List<Movimiento>) {

    if (movimientos.isEmpty()) {
        println("No hay movimientos registrados.")
        return
    }

    println("\n--- Movimientos registrados ---")

    movimientos.forEachIndexed { index, movimiento ->
        val signo = if (movimiento.tipo == "Ingreso") "+" else "-"

        println(
            "${index + 1}. ${movimiento.tipo} | " +
                    "${movimiento.categoria} | " +
                    "${movimiento.descripcion} | " +
                    "$signo$${movimiento.monto}"
        )
    }
}

fun mostrarSaldo(movimientos: List<Movimiento>) {

    val ingresos = movimientos
        .filter { it.tipo == "Ingreso" }
        .sumOf { it.monto }

    val gastos = movimientos
        .filter { it.tipo == "Gasto" }
        .sumOf { it.monto }

    val saldo = ingresos - gastos

    println("\n--- Resumen financiero ---")
    println("Total de ingresos: $$ingresos")
    println("Total de gastos: $$gastos")
    println("Saldo actual: $$saldo")
}

    fun mostrarResumenPorCategoria(movimientos: List<Movimiento>) {

        if (movimientos.isEmpty()) {
            println("No hay movimientos para resumir.")
            return
        }

        val resumen = movimientos.groupBy { it.categoria }

        println("\n--- Resumen por categoría ---")

        resumen.forEach { (categoria, lista) ->
            val total = lista.sumOf { it.monto }
            println("$categoria: $$total")
        }
    }

fun eliminarMovimiento(movimientos: MutableList<Movimiento>) {

    if (movimientos.isEmpty()) {
        println("No hay movimientos para eliminar.")
        return
    }

    mostrarMovimientos(movimientos)

    print("Número del movimiento a eliminar: ")

    val indice = readLine()?.toIntOrNull()

    if (indice == null || indice <= 0 || indice > movimientos.size) {
        println("Número inválido.")
        return
    }

    movimientos.removeAt(indice - 1)

    println("Movimiento eliminado correctamente.")
}