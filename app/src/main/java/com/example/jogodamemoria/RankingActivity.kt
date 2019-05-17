package com.example.jogodamemoria

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class RankingActivity : AppCompatActivity() {
    private lateinit var rankingLvJogadores: ListView
    private lateinit var jogadores: ArrayList<Jogador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        rankingLvJogadores = findViewById(R.id.rankingLvJogadores)
        jogadores = intent.getSerializableExtra(
            "JOGADORES") as ArrayList<Jogador>
        rankingLvJogadores.adapter = ListExampleAdapter(this@RankingActivity)
    }

    inner class ListExampleAdapter(context: Context) : BaseAdapter() {
        private val inflator: LayoutInflater

        init {
            this.inflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return jogadores.size
        }

        override fun getItem(position: Int): Any {
            return jogadores[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view = this.inflator.inflate(R.layout.activity_jogador, parent, false)

            val jogadorTvNome = view.findViewById(R.id.jogadorTvNome) as TextView
            val jogadorTvVitorias = view.findViewById(R.id.jogadorTvVitorias) as TextView
            val jogadorTvPosicao = view.findViewById(R.id.jogadorTvPosicao) as TextView

            val jogador = jogadores[position]

            jogadorTvNome.setText("Jogador: ${jogador.nome}")
            jogadorTvVitorias.setText("Vitórias: ${jogador.vitorias}")
            jogadorTvPosicao.setText("${position+1}")

            return view
        }
    }
}
