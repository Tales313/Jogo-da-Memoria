package com.example.jogodamemoria

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var JOGO = 1
    private var isLogado = false

    private lateinit var dao: JogadorDAO
    private lateinit var jogadorLogado: Jogador

    private lateinit var mainBtJogar: Button
    private lateinit var mainBtListagem: Button
    private lateinit var mainEtLogin: EditText
    private lateinit var mainBtLogin: Button
    private lateinit var mainTvMsgLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = JogadorDAO(this@MainActivity)

        mainBtJogar = findViewById(R.id.mainBtJogar)
        mainBtListagem = findViewById(R.id.mainBtListagem)
        mainEtLogin = findViewById(R.id.mainEtLogin)
        mainBtLogin = findViewById(R.id.mainBtLogin)
        mainTvMsgLogin = findViewById(R.id.mainTvMsgLogin)

        mainBtJogar.setOnClickListener{novaPartida(it)}
        mainBtListagem.setOnClickListener{listagem(it)}
        mainBtLogin.setOnClickListener{logar(it)}
    }

    private fun logar(view: View?) {
        val nome = mainEtLogin.text.toString()
        if(nome.length < 3) {
            val msg = "Digite pelo menos 3 caracteres!"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        } else {
            val jogador = dao.get(nome)
            if(jogador != null) {
                jogadorLogado = jogador
                Toast.makeText(this@MainActivity, "${jogador.nome} Logado!", Toast.LENGTH_SHORT).show()
            }else {
                dao.insert(Jogador(nome))
                jogadorLogado = dao.get(nome)!!
                Toast.makeText(this@MainActivity, "Novo jogador cadastrado!", Toast.LENGTH_SHORT).show()
            }
            mainTvMsgLogin.text = "${jogadorLogado.nome} tem ${jogadorLogado.vitorias} vitórias."
            isLogado = true
        }
    }

    private fun listagem(view: View?) {
        val it = Intent(this@MainActivity, ListagemActivity::class.java)
        startActivity(it)
    }

    private fun novaPartida(view: View?) {
        if(!isLogado)
            Toast.makeText(this@MainActivity, "Faça Login!", Toast.LENGTH_SHORT).show()
        else {
            val it = Intent(this@MainActivity, JogoActivity::class.java)
            it.putExtra("JOGADOR", jogadorLogado)
            startActivityForResult(it, JOGO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == JOGO) {
                jogadorLogado = data?.getSerializableExtra("JOGADOR") as Jogador
                mainTvMsgLogin.text = "${jogadorLogado.nome} tem ${jogadorLogado.vitorias} vitórias."
                dao.update(jogadorLogado)
            }
        }
    }

}
