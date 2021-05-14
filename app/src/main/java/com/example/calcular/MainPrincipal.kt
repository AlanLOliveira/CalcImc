package com.example.calcular


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainPrincipal : AppCompatActivity() {
    private var imc: Float = 0.0f
    lateinit var mensagemIMC: String
    lateinit var txtPeso: EditText
    lateinit var txtAltura: EditText
    lateinit var btCalcular: Button
    lateinit var btLimpar: Button
    lateinit var resultadoImc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainprincipal)

        carregarElementos()
        calcular()
        clear()
    }

    fun carregarElementos() {
        txtPeso = findViewById(R.id.textPeso)
        txtAltura = findViewById(R.id.textAltura)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)
        resultadoImc = findViewById(R.id.resultadoImc)
    }


    @SuppressLint("SetTextI18n")
    fun calcular() {
        btCalcular.setOnClickListener {
            if (validarEntrada()) {
                try {
                    val peso = txtPeso.text.toString().toFloat()
                    val altura = txtAltura.text.toString().toFloat()
                    imc = peso / (altura * altura)
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(this, "Digitação incorreta", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
            } else {
                Toast.makeText(this, "Insira os valores", Toast.LENGTH_SHORT).show()
            }

            mensagemIMC = if (imc < 18.5) {
                "Abaixo do peso"
            } else if (imc > 18.5 && imc <= 24.9) {
                "Normal"
            } else if (imc > 24.9 && imc <= 29.9) {
                "Sobrepeso"
            } else if (imc > 29.9 && imc <= 34.9) {
                "Obesidade (Grau I)"
            } else if (imc > 34.9 && imc <= 39.9) {
                "Obsidade Severa (Grau II)"
            } else "Obsidade Móbida"

            this.resultadoImc.text =
                "Seu IMC é: ${"%.2f".format(imc)} \nSua classificação é de $mensagemIMC"
        }
    }

    fun validarEntrada(): Boolean {
        return (txtPeso.text.toString() != "" && txtAltura.text.toString() != "")
    }

    fun clear() {
        btLimpar.setOnClickListener {
            this.txtPeso.text.clear()
           this.txtAltura.text.clear()
            this.resultadoImc.text = ""
        }
    }
}




















































