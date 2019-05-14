package com.example.jogodamemoria

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var dao: ProfessorDAO

    private lateinit var mainBtJogar: Button
    private lateinit var mainBtListagem: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // dao = ProfessorDAO(this@MainActivity)

        mainBtJogar = findViewById(R.id.mainBtJogar)
        mainBtListagem = findViewById(R.id.mainBtListagem)

        mainBtJogar.setOnClickListener{novaPartida(it)}
        mainBtListagem.setOnClickListener{listagem(it)}
    }

    private fun listagem(view: View?) {
        val it = Intent(this@MainActivity, ListagemActivity::class.java)
        startActivity(it)
    }

    private fun novaPartida(view: View?) {
        val it = Intent(this@MainActivity, JogoActivity::class.java)
        startActivity(it)
    }

}
