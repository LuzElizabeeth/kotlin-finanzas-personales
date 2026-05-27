# Kotlin Finanzas Personales

Aplicación desarrollada en Kotlin para registrar ingresos y gastos personales, calcular saldo, filtrar movimientos y organizar información.

--------------------------------------

## Integrantes del equipo: 
- Quintana Castro Luz Elizabeth
- Ramírez Aguilar Rodolfo Eduardo
- Fuentes Carmona Juan Diego
- Puerto Riegos Magdalena Noemi
- Yam Reyes Maritza Guadalupe

--------------------------------------

## Funcionalidades principales

La aplicación permite:

- Registrar ingresos.
- Registrar gastos.
- Mostrar movimientos registrados.
- Calcular saldo total.
- Filtrar gastos mayores a una cantidad.
- Mostrar resumen por categoría.
- Eliminar movimientos.
- Validar datos incorrectos.

--------------------------------------

## Tecnologías utilizadas

- Kotlin
- IntelliJ IDEA Community
- Git y GitHub

--------------------------------------

## Instrucciones de instalación y ejecución

### Clonar repositorio

git clone https://github.com/LuzElizabeeth/kotlin-finanzas-personales.git

### Abrir proyecto

Abrir la carpeta del proyecto en IntelliJ IDEA Community.

### Ejecutar aplicación

Ejecutar el archivo: "Main.kt" o utilizar el botón Run de IntelliJ.

--------------------------------------

## Conceptos de Kotlin aplicados

Funciones: mostrarSaldo(), agregarMovimiento(), eliminarMovimiento()
Condicionales: if y when
Ciclos: do while y forEach
Colecciones: MutableList y List
Operaciones funcionales: filter, sumOf, groupBy, forEachIndexed
Null safety: ?. ?: toDoubleOrNull()
Data class: data class Movimiento

--------------------------------------
# Versiones del proyecto

El repositorio contiene dos versiones del proyecto:

## Versión de consola

La primera versión fue desarrollada en IntelliJ IDEA como aplicación de consola. Permite registrar ingresos, gastos, calcular saldo, filtrar movimientos y eliminar registros.

Ubicación:
text
consola-intellij/

## Versión Android
La segunda versión fue desarrollada en Android Studio con Kotlin y Jetpack Compose. Incluye una interfaz visual para registrar ingresos y gastos personales, mostrar saldo, validar datos y visualizar movimientos.

Ubicación:
text
android-studio/

-------------------------------------

## Reflexión de proceso

### ¿Qué fue lo más difícil del proyecto y cómo se resolvió?

Una de las partes más difíciles fue entender cómo organizar las funciones sin colocar toda la lógica dentro del main. También surgieron problemas al trabajar con GitHub porque varias personas realizaban cambios al mismo tiempo y aparecieron errores al hacer push. Esto se resolvió utilizando git pull antes de subir cambios y organizando mejor las tareas de cada integrante.

### ¿Qué concepto de Kotlin resultó más interesante?

El manejo de null safety fue uno de los conceptos más interesantes porque ayuda a evitar errores comunes relacionados con valores nulos. También resultó útil aprender funciones como filter y groupBy para trabajar con listas de forma más sencilla.

### ¿Qué mejoras podrían agregarse en el futuro?

En futuras versiones podría agregarse almacenamiento en archivos o bases de datos para guardar los movimientos incluso después de cerrar el programa. También podría desarrollarse una interfaz gráfica para hacer la aplicación más visual y fácil de utilizar.

### ¿Qué se aprendió con este proyecto?

El proyecto permitió entender mejor cómo estructurar una aplicación pequeña en Kotlin utilizando funciones, listas, validaciones y operaciones funcionales. Además, ayudó a practicar el trabajo colaborativo mediante GitHub y el uso de commits frecuentes.

--------------------------------------

## Historial de trabajo colaborativo

Cada integrante participó realizando cambios y commits dentro del repositorio compartido en GitHub, utilizando Git para el control de versiones y organización del proyecto.