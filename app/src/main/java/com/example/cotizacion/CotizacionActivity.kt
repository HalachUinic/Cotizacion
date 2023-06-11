package com.example.cotizacion

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.random.Random

class Cotizacion_Activity : AppCompatActivity() {

    private lateinit var lblNombre: TextView
    private lateinit var lblFolio: TextView
    private lateinit var lblEngache: TextView
    private lateinit var lblPagoM: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtValor: EditText
    private lateinit var txtPorcentaje: EditText
    private lateinit var rb1: RadioButton
    private lateinit var rb2: RadioButton
    private lateinit var rb3: RadioButton
    private lateinit var rb4: RadioButton
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button
    private val coti = cotizacion(0, "", 0.0, 0.0, 0)
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotizacion)
        iniciar()

        val intent = intent
        val nombre = intent.getStringExtra("nombre")
        lblNombre.text = "Nombre Cliente: $nombre"

        val folio: Int
        val numero = Random.nextInt(1, 11)
        lblFolio.text = "Folio:$numero"

        btnCalcular.setOnClickListener {
            if (txtDescripcion.text.toString() == "" || txtValor.text.toString() == "" || txtPorcentaje.text.toString() == "") {
                Toast.makeText(applicationContext, "No deje campos vacios", Toast.LENGTH_SHORT).show()
            } else {
                btnCotizar()
            }
        }

        btnLimpiar.setOnClickListener { btnLimpiar() }

        btnRegresar.setOnClickListener { btnRegresar() }
    }

    private fun iniciar() {
        lblNombre = findViewById(R.id.lblNombre)
        lblFolio = findViewById(R.id.lblFolio)
        lblEngache = findViewById(R.id.lblPagoI)
        lblPagoM = findViewById(R.id.lblPagoM)
        txtDescripcion = findViewById(R.id.txtDescricion)
        txtPorcentaje = findViewById(R.id.txtPorcentaje)
        txtValor = findViewById(R.id.txtValorAuto)
        btnCalcular = findViewById(R.id.btnCotizar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rb3 = findViewById(R.id.rb3)
        rb4 = findViewById(R.id.rb4)
    }

    private fun btnCotizar() {
        var meses = 0
        coti.valorAuto = txtValor.text.toString().toDouble()
        coti.porEnganche = txtPorcentaje.text.toString().toDouble()
        meses = when {
            rb1.isChecked -> 1
            rb2.isChecked -> 2
            rb3.isChecked -> 3
            rb4.isChecked -> 4
            else -> 0
        }
        coti.plazo = meses
        val enganche = coti.calcularEnganche()
        lblEngache.text = "El Enganche es: ${decimalFormat.format(enganche)}"
        val pagom = coti.calcularPagoMensual()
        lblPagoM.text = "El Pago mensual es: ${decimalFormat.format(pagom)}"
    }

    private fun btnLimpiar() {
        txtPorcentaje.setText("")
        txtValor.setText("")
        txtDescripcion.setText("")
        lblPagoM.setText("")
        lblEngache.setText("")
    }

    private fun btnRegresar() {
        val confirmar = AlertDialog.Builder(this)
        confirmar.setTitle("Cotizacion")
        confirmar.setMessage("¿Desea Salir?")
        confirmar.setPositiveButton("Confirmar") { dialog, which ->
            finish()
        }
        confirmar.setNegativeButton("Cancelar") { dialog, which ->
            // No hacer nada
        }
        confirmar.show()
    }

}
