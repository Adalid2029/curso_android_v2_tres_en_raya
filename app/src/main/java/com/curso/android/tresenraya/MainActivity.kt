package com.curso.android.tresenraya

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tablero: Array<Array<String>>
    private lateinit var indicadorJugadorActual: TextView
    private lateinit var btnReiniciarJuego: Button
    private lateinit var botones: Array<Button>
    private val filasTablero = 3
    private val columnasTablero = 3
    private var jugadorActual = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        indicadorJugadorActual = findViewById(R.id.indicadorJugadorActual)
        btnReiniciarJuego = findViewById(R.id.reiniciarJuego)
        tablero = Array(filasTablero) { Array(columnasTablero) { "" } }

        botones = arrayOf(
            findViewById(R.id.boton1),
            findViewById(R.id.boton2),
            findViewById(R.id.boton3),
            findViewById(R.id.boton4),
            findViewById(R.id.boton5),
            findViewById(R.id.boton6),
            findViewById(R.id.boton7),
            findViewById(R.id.boton8),
            findViewById(R.id.boton9),
        )
        for (i in botones.indices) {
            val fila = i / filasTablero
            val columna = i % columnasTablero
            botones[i].setOnClickListener {
                turnos(fila, columna, botones[i])
            }
        }
        empezarJuego()
        reiniciarJuego()
    }

    private fun empezarJuego() {
        for (x in 0 until filasTablero) {
            for (y in 0 until columnasTablero) {
                tablero[x][y] = " "
            }
        }
    }

    private fun crearTablero() {}
    private fun turnos(fila: Int, columna: Int, boton: Button) {
        if (boton.text == "") {
            if (jugadorActual == 1) {
                tablero[fila][columna] = "X"
                boton.text = "X"
                jugadorActual = 2
                indicadorJugadorActual.text = "Turno del Jugador 2"
                comprobarVictoria();
            } else {
                tablero[fila][columna] = "O"
                boton.text = "O"
                jugadorActual = 1
                indicadorJugadorActual.text = "Turno del Jugador 1"
                comprobarVictoria();
            }

        }
    }

    private fun comprobarVictoria() {
        if (comprobarDiagonales() || comprobarFilas() || comprobarColumnas()) {
            if (jugadorActual == 1) {
                indicadorJugadorActual.text = "El jugador 2 ha ganado"
            } else {
                indicadorJugadorActual.text = "El jugador 1 ha ganado"
            }
            btnReiniciarJuego.visibility = Button.VISIBLE
            deshabilitarBotones(false)
        }
    }

    private fun deshabilitarBotones(b: Boolean) {
        for (i in botones.indices) {
            botones[i].isEnabled = b
        }
    }

    private fun comprobarDiagonales(): Boolean {
        return (
                (tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] != " ") ||
                        (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] != " ")
                )
    }

    private fun comprobarFilas(): Boolean {
        for (i in 0 until filasTablero) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] != " ") {
                return true
            }
        }
        return false
    }

    private fun comprobarColumnas(): Boolean {
        for (i in 0 until columnasTablero) {
            if (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] != " ") {
                return true
            }
        }
        return false
    }

    private fun reiniciarJuego() {
        btnReiniciarJuego.setOnClickListener() {
            empezarJuego()
            for (boton in botones) {
                boton.text = ""
            }
            btnReiniciarJuego.visibility = Button.INVISIBLE
            deshabilitarBotones(true)
        }
    }
}