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
    private lateinit var professores: ArrayList<Professor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        listagemLvProfessores = findViewById(R.id.listagemLvProfessores)
        professores = intent.getSerializableExtra(
            "PROFESSORES") as ArrayList<Professor>
        listagemLvProfessores.adapter = ListExampleAdapter(this@ListagemActivity)
    }

    inner class ListExampleAdapter(context: Context) : BaseAdapter() {
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
