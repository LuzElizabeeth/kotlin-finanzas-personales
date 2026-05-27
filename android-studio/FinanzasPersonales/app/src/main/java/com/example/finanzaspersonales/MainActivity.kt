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

data class Movimiento(
    val tipo: String,
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

    var descripcion by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }

    var saldo by remember { mutableStateOf(0.0) }

    val movimientos = remember {
        mutableStateListOf<Movimiento>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Finanzas Personales",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Saldo actual: $$saldo",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                descripcion = it
            },
            label = {
                Text("Descripción")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = monto,
            onValueChange = {
                monto = it
            },
            label = {
                Text("Monto")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = {

                    val cantidad = monto.toDoubleOrNull() ?: 0.0

                    saldo += cantidad

                    movimientos.add(
                        Movimiento(
                            "Ingreso",
                            descripcion,
                            cantidad
                        )
                    )

                    descripcion = ""
                    monto = ""
                }
            ) {
                Text("Agregar ingreso")
            }

            Button(
                onClick = {

                    val cantidad = monto.toDoubleOrNull() ?: 0.0

                    saldo -= cantidad

                    movimientos.add(
                        Movimiento(
                            "Gasto",
                            descripcion,
                            cantidad
                        )
                    )

                    descripcion = ""
                    monto = ""
                }
            ) {
                Text("Agregar gasto")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Movimientos",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            items(movimientos) { movimiento ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {

                        Text(text = movimiento.tipo)

                        Text(text = movimiento.descripcion)

                        Text(text = "$${movimiento.monto}")
                    }
                }
            }
        }
    }
}