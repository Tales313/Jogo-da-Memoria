package com.example.jogodamemoria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class JogoActivity : AppCompatActivity() {
    private lateinit var jogoGl: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        this.jogoGl = findViewById(R.id.jogoGl)
    }
}
