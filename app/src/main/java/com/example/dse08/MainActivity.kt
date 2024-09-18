package com.example.dse08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ageInput = findViewById<EditText>(R.id.age_input)
        val sendButton = findViewById<Button>(R.id.send_button)
        val imageView = findViewById<ImageView>(R.id.image_view)

        val ageRanges = mapOf(
            "Bebé" to listOf(0, 1),
            "Niño" to listOf(2, 12),
            "Adolescente" to listOf(13, 17),
            "Adulto" to listOf(18, 64),
            "Adulto mayor" to listOf(65, Int.MAX_VALUE)
        )

        sendButton.setOnClickListener {
            val ageText = ageInput.text.toString()
            if (ageText.isNotEmpty()) {
                val age = ageText.toInt()
                val personType = ageRanges.entries.find { it.value[0] <= age && it.value[1] >= age }?.key
                val imageId = when (personType) {
                    "Bebé" -> R.drawable.bebe
                    "Niño" -> R.drawable.nino
                    "Adolescente" -> R.drawable.adolescente
                    "Adulto" -> R.drawable.adulto
                    "Adulto mayor" -> R.drawable.adulto_mayor
                    else -> null
                }
                personType?.let {
                    Toast.makeText(this, "Eres un $it", Toast.LENGTH_SHORT).show()
                    imageId?.let { id -> imageView.setImageResource(id) }
                } ?: run {
                    Toast.makeText(this, "Edad fuera de rango", Toast.LENGTH_SHORT).show()
                    imageView.setImageDrawable(null)
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa una edad", Toast.LENGTH_SHORT).show()
            }
        }
    }
}