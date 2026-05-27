package com.example.finanzaspersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

data class Movimiento(
    val tipo: String,
    val categoria: String,
    val descripcion: String,
    val monto: Double
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FinanzasScreen()
        }
    }
}

@Composable
fun FinanzasScreen() {

    var categoria by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    var saldo by remember { mutableStateOf(0.0) }

    val movimientos = remember {
        mutableStateListOf<Movimiento>()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color(0xFFDCE8D2)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Finanzas Personales",
                fontSize = 30.sp,
                color = androidx.compose.ui.graphics.Color(0xFF3D5A2B)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Saldo actual",
                        fontSize = 18.sp,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "$$saldo",
                        fontSize = 38.sp,
                        color = androidx.compose.ui.graphics.Color(0xFF4E7A38)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                },
                label = {
                    Text("Descripción")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = monto,
                onValueChange = {
                    monto = it
                },
                label = {
                    Text("Monto")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    onClick = {
                        if (categoria.isBlank() || descripcion.isBlank() || monto.isBlank()) {
                            mensajeError = "Completa todos los campos"
                            return@Button
                        }

                        val cantidad = monto.toDoubleOrNull()

                        if (cantidad == null || cantidad <= 0) {
                            mensajeError = "Ingresa un monto válido"
                            return@Button
                        }

                        mensajeError = ""
                        saldo += cantidad

                        movimientos.add(
                            Movimiento(
                                tipo = "Ingreso",
                                categoria = categoria,
                                descripcion = descripcion,
                                monto = cantidad
                            )
                        )

                        categoria = ""
                        descripcion = ""
                        monto = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFF6F9B58)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text("Ingreso")
                }

                if (mensajeError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = mensajeError,
                        color = androidx.compose.ui.graphics.Color(0xFFB85C5C),
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = {
                        if (categoria.isBlank() || descripcion.isBlank() || monto.isBlank()) {
                            mensajeError = "Completa todos los campos"
                            return@Button
                        }

                        val cantidad = monto.toDoubleOrNull()

                        if (cantidad == null || cantidad <= 0) {
                            mensajeError = "Ingresa un monto válido"
                            return@Button
                        }

                        mensajeError = ""
                        saldo -= cantidad

                        movimientos.add(
                            Movimiento(
                                tipo = "Gasto",
                                categoria = categoria,
                                descripcion = descripcion,
                                monto = cantidad
                            )
                        )

                        categoria = ""
                        descripcion = ""
                        monto = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFFB85C5C)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text("Gasto")
                }

                if (mensajeError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = mensajeError,
                        color = androidx.compose.ui.graphics.Color(0xFFB85C5C),
                        fontSize = 14.sp
                    )
                }
            }

            if (mensajeError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = mensajeError,
                    color = androidx.compose.ui.graphics.Color(0xFFB85C5C),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Movimientos",
                fontSize = 26.sp,
                color = androidx.compose.ui.graphics.Color(0xFF3D5A2B)
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn {

                items(movimientos) { movimiento ->

                    val colorTarjeta =
                        if (movimiento.tipo == "Ingreso")
                            androidx.compose.ui.graphics.Color(0xFFE7F5E4)
                        else
                            androidx.compose.ui.graphics.Color(0xFFFBE4E4)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorTarjeta
                        ),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = movimiento.tipo,
                                    fontSize = 18.sp,
                                    color = if (movimiento.tipo == "Ingreso")
                                        androidx.compose.ui.graphics.Color(0xFF4E7A38)
                                    else
                                        androidx.compose.ui.graphics.Color(0xFFB85C5C)
                                )

                                Text(
                                    text = "$${movimiento.monto}",
                                    fontSize = 18.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = movimiento.descripcion,
                                fontSize = 15.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    movimientos.remove(movimiento)

                                    saldo = movimientos.sumOf {
                                        if (it.tipo == "Ingreso") it.monto else -it.monto
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = androidx.compose.ui.graphics.Color(0xFF6F7F5F)
                                )
                            ) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}