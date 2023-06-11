package com.example.cotizacion

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnIngresar: Button
    private lateinit var txtNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciar()

        btnIngresar.setOnClickListener {
            btnIngresar()
        }
    }

    private fun iniciar() {
        btnIngresar = findViewById(R.id.btnIniciar)
        txtNombre = findViewById(R.id.txtNombre)
    }

    private fun btnIngresar() {
        if (txtNombre.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "El campo está vacío", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this@MainActivity, Cotizacion_Activity::class.java)
            val nombre = txtNombre.text.toString()
            intent.putExtra("nombre", nombre)
            startActivity(intent)
        }
    }
}
