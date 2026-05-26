package com.example.finanzaspersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Movimiento(
    val tipo: String,
    val categoria: String,
    val descripcion: String,
    val monto: Double
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    val movimientos = remember { mutableStateListOf<Movimiento>() }

    val saldo = movimientos.sumOf {
        if (it.tipo == "Ingreso") it.monto else -it.monto
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Control de Finanzas Personales",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val montoDouble = monto.toDoubleOrNull()

                if (montoDouble != null) {

                    movimientos.add(
                        Movimiento(
                            "Ingreso",
                            categoria,
                            descripcion,
                            montoDouble
                        )
                    )

                    categoria = ""
                    descripcion = ""
                    monto = ""
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar ingreso")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                val montoDouble = monto.toDoubleOrNull()

                if (montoDouble != null) {

                    movimientos.add(
                        Movimiento(
                            "Gasto",
                            categoria,
                            descripcion,
                            montoDouble
                        )
                    )

                    categoria = ""
                    descripcion = ""
                    monto = ""
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar gasto")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Saldo actual: $$saldo",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        movimientos.forEach {

            Text(
                text = "${it.tipo} | ${it.categoria} | ${it.descripcion} | $${it.monto}"
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
