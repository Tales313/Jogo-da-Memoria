package com.example.jogodamemoria

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class ListagemActivity : AppCompatActivity() {
    private lateinit var listagemLvProfessores: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        listagemLvProfessores = findViewById(R.id.listagemLvProfessores)
        listagemLvProfessores.adapter = ListExampleAdapter(this@ListagemActivity)
    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        internal var professores = arrayOf(
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
        private val inflator: LayoutInflater

        init {
            this.inflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return professores.size
        }

        override fun getItem(position: Int): Any {
            return professores[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view = this.inflator.inflate(R.layout.activity_professor, parent, false)

            val profIvFoto = view.findViewById(R.id.profIvFoto) as ImageView
            val profTvNome = view.findViewById(R.id.profTvNome) as TextView
            val profTvDisciplina = view.findViewById(R.id.profTvDisciplina) as TextView

            val prof = professores[position]

            profIvFoto.setImageResource(prof.foto)
            profTvNome.text = prof.nome
            profTvDisciplina.text = prof.disciplina

            profIvFoto.setImageResource(prof.foto)

            return view
        }
    }
}
