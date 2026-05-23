fun main() {
    var opcion: String?

    do {
        mostrarMenu()
        opcion = readLine()

        when (opcion) {
            "1" -> println("Agregar ingreso")
            "2" -> println("Agregar gasto")
            "3" -> println("Ver movimientos")
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