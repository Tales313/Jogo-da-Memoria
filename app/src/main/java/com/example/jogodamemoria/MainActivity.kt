package com.example.jogodamemoria

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    private lateinit var mainBtRanking: Button
    private lateinit var mainBtListagem: Button
    private lateinit var mainTvLogin: TextView
    private lateinit var mainEtLogin: EditText
    private lateinit var mainBtLogin: Button
    private lateinit var mainTvMsgLogin: TextView
    private lateinit var mainBtLogout: Button

    private lateinit var professores: ArrayList<Professor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = JogadorDAO(this@MainActivity)

        mainBtJogar = findViewById(R.id.mainBtJogar)
        mainBtRanking = findViewById(R.id.mainBtRanking)
        mainBtListagem = findViewById(R.id.mainBtListagem)
        mainTvLogin = findViewById(R.id.mainTvLogin)
        mainEtLogin = findViewById(R.id.mainEtLogin)
        mainBtLogin = findViewById(R.id.mainBtLogin)
        mainTvMsgLogin = findViewById(R.id.mainTvMsgLogin)
        mainBtLogout = findViewById(R.id.mainBtLogout)

        mainBtJogar.setOnClickListener{novaPartida(it)}
        mainBtRanking.setOnClickListener{ranking(it)}
        mainBtListagem.setOnClickListener{listagem(it)}
        mainBtLogin.setOnClickListener{logar(it)}
        mainBtLogout.setOnClickListener{deslogar(it)}

        professores = arrayListOf(
            Professor(
                "Alana Marques de Morais",
                "Padrões de Projeto de Software",
                R.drawable.alana
            ),
            Professor(
                "Alex Sandro da Cunha Rego",
                "Algoritmo e Programação Estruturada",
                R.drawable.alex
            ),
            Professor(
                "Candido José Ramos do Egypto",
                "Algoritmo e Programação Estruturada",
                R.drawable.candido
            ),
            Professor(
                "Crishane Azevedo Freire",
                "Algoritmo e Programação Estruturada",
                R.drawable.crishane
            ),
            Professor(
                "Damires Yluska de Souza Fernandes",
                "Banco de Dados II",
                R.drawable.damires
            ),
            Professor(
                "Denio Mariz Timoteo de Sousa",
                "Segurança de Dados",
                R.drawable.denio
            ),
            Professor(
                "Edemberg Rocha da Silva",
                "Algoritmo e Programação Estruturada",
                R.drawable.edemberg
            ),
            Professor(
                "Fausto Veras Maranhão Ayres",
                "Programação Orientada a Objetos",
                R.drawable.fausto
            ),
            Professor(
                "Francisco Dantas Nobre Neto",
                "Linguagens de Marcação",
                R.drawable.franciscodantas
            ),
            Professor(
                "Francisco Petrônio Alencar de Medeiros",
                "Interação Humano-Computador",
                R.drawable.franciscopetronio
            ),
            Professor(
                "Frederico Costa Guedes Pereira",
                "Programação Para Web II",
                R.drawable.fred
            ),
            Professor(
                "Giovanni Loureiro Franca de Mendonca",
                "Fundamentos da Computação",
                R.drawable.giovanni
            ),
            Professor(
                "Gustavo Wagner Diniz Mendes",
                "Sistemas Operacionais",
                R.drawable.gustavo
            ),
            Professor(
                "Heremita Brasileiro Lira",
                "Gerência de Projetos de Software",
                R.drawable.heremita
            ),
            Professor(
                "Juliana Dantas Ribeiro Viana de Medeiros",
                "Desenvolvimento e Execução de Projetos de Software",
                R.drawable.juliana
            ),
            Professor(
                "Lafayette Batista Melo",
                "Fundamentos da Metodologia Científica",
                R.drawable.lafa
            ),
            Professor(
                "Leonidas Francisco de Lima Junior",
                "Protocolos e Interconexão de Redes de Computadores",
                R.drawable.leonidas
            ),
            Professor(
                "Luiz Carlos Rodrigues Chaves",
                "Linguagens de Script",
                R.drawable.luiz
            ),
            Professor(
                "Marcus Vinicius Delgado Varandas",
                "Empreendedorismo",
                R.drawable.marcusvarandas
            ),
            Professor(
                "Nilton Freire Santos",
                "Banco de Dados I",
                R.drawable.nilton
            ),
            Professor(
                "Pryscilla Marcili Dora",
                "Fundamentos de Redes de Computadores",
                R.drawable.pryscilla
            ),
            Professor(
                "Thiago Jose Marques Moura",
                "Linguagens de Marcação",
                R.drawable.thiago
            ),
            Professor(
                "Valeria Maria Bezerra Cavalcanti Maciel",
                "Programação Para Dispositivos Móveis",
                R.drawable.valeria
            )
        )
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
            mainEtLogin.setText("")
            visibilidadeComponentes("login")
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    private fun deslogar(view: View?) {
        if(!isLogado)
            Toast.makeText(this@MainActivity, "Já está deslogado!", Toast.LENGTH_SHORT).show()
        else {
            isLogado = false
            mainTvMsgLogin.text = "Faça login para jogar!"
            visibilidadeComponentes("logoff")
        }
    }

    private fun visibilidadeComponentes(logar: String) {
        if(logar == "login") {
            mainEtLogin.visibility = View.INVISIBLE
            mainBtLogin.visibility = View.INVISIBLE
            mainBtLogout.visibility = View.VISIBLE
            mainTvLogin.visibility = View.INVISIBLE
            mainBtJogar.visibility = View.VISIBLE
        }else if(logar == "logoff"){
            mainEtLogin.visibility = View.VISIBLE
            mainBtLogin.visibility = View.VISIBLE
            mainBtLogout.visibility = View.INVISIBLE
            mainTvLogin.visibility = View.VISIBLE
            mainBtJogar.visibility = View.INVISIBLE
        }
    }

    private fun ranking(view: View?) {
        val it = Intent(this@MainActivity, RankingActivity::class.java)
        it.putExtra("JOGADORES", dao.get())
        startActivity(it)
    }

    private fun listagem(view: View?) {
        val it = Intent(this@MainActivity, ListagemActivity::class.java)
        it.putExtra("PROFESSORES", this.professores)
        startActivity(it)
    }

    private fun novaPartida(view: View?) {
        if(!isLogado)
            Toast.makeText(this@MainActivity, "Faça Login!", Toast.LENGTH_SHORT).show()
        else {
            val it = Intent(this@MainActivity, JogoActivity::class.java)
            it.putExtra("JOGADOR", jogadorLogado)
            it.putExtra("PROFESSORES", this.professores)
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
