package com.example.imccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText

const val EXTRA_VALOR_IMC = "VALOR_IMC"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Acessa o campo de peso e altura do activity_main.xml
        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)

        // Acessa o botão Calcular do activity_main.xml
        val btnCalcularIMC = findViewById<Button>(R.id.btnCalcularIMC)

        // Executa o código quando o usuário toca no botão Calcular
        btnCalcularIMC.setOnClickListener {

            // Lê o peso do campo edtPeso do activity_main.xml
            // e tenta converter o texto para um número decimal
            val peso = edtPeso.text.toString().toDoubleOrNull()

            // Verifica se o peso está vazio, inválido ou é menor ou igual a zero
            if (peso == null || peso <= 0) {
                edtPeso.error = "Digite um peso válido"
                return@setOnClickListener
            }

            // Lê a altura do campo edtAltura do activity_main.xml
            // e tenta converter o texto para um número decimal
            val altura = edtAltura.text.toString().toDoubleOrNull()

            // Verifica se a altura está vazia, inválida ou é menor ou igual a zero
            if (altura == null || altura <= 0) {
                edtAltura.error = "Digite uma altura válida"
                return@setOnClickListener
            }

                // Calcula o IMC dividindo o peso pela altura ao quadrado
                val imc = peso / (altura * altura)

                // Prepara a navegação para a tela ResultadoActivity
                val intent = Intent(this, ResultadoActivity::class.java)

                // Envia o valor calculado do IMC para a ResultadoActivity
                intent.putExtra(EXTRA_VALOR_IMC, imc)

                // Abre a ResultadoActivity com o valor do IMC enviado
                startActivity(intent)
            }

        }
    }