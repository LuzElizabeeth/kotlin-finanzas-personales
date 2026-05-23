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
            "4" -> println("Ver saldo")
            "5" -> println("Filtrar gastos")
            "6" -> println("Saliendo del programa...")
            else -> println("Opción no válida.")
        }

    } while (opcion != "6")
}

fun mostrarMenu() {
    println("\n--- Control de Finanzas Personales ---")
    println("1. Agregar ingreso")
    println("2. Agregar gasto")
    println("3. Ver movimientos")
    println("4. Ver saldo")
    println("5. Filtrar gastos mayores a una cantidad")
    println("6. Salir")
    print("Elige una opción: ")
}

fun agregarMovimiento(movimientos: MutableList<Movimiento>, tipo: String) {
    print("Categoría: ")
    val categoria = readLine()?.takeIf { it.isNotBlank() } ?: "General"

    print("Descripción: ")
    val descripcion = readLine()?.takeIf { it.isNotBlank() } ?: "Sin descripción"

    print("Monto: ")
    val monto = readLine()?.toDoubleOrNull()

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

    movimientos.forEachIndexed { index, movimiento ->

        println(
            "${index + 1}. " +
                    "${movimiento.tipo} | " +
                    "${movimiento.categoria} | " +
                    "${movimiento.descripcion} | " +
                    "$${movimiento.monto}"
        )
    }
}