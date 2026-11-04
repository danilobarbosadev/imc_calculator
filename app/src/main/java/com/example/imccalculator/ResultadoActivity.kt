package com.example.imccalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale
import android.view.MenuItem

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Acessa a Toolbar do activity_resultado.xml
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarResultado)

        // Define a Toolbar como barra de navegação da Activity
        setSupportActionBar(toolbar)
        // Exibe a seta de voltar na Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Recupera o valor do IMC enviado pela MainActivity
        val imc = intent.getDoubleExtra(EXTRA_VALOR_IMC, 0.0)

        // Acessa o campo de resultado do activity_resultado.xml
        val tvResultadoIMC = findViewById<TextView>(R.id.tvResultadoIMC)

        // Mostra o valor do IMC na tela
        tvResultadoIMC.text = String.format(Locale.forLanguageTag("pt-BR"), "%.2f", imc)

        // Classifica o IMC de acordo com o valor calculado
        val classificacao = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }

            // Acessa o texto de classificação do activity_resultado.xml
            val tvClassificacaoIMC = findViewById<TextView>(R.id.tvClassificacaoIMC)

            // Mostra a classificação do IMC no activity_resultado.xml
            tvClassificacaoIMC.text = classificacao
        }

    // Executa a ação quando o usuário toca na seta da Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    }