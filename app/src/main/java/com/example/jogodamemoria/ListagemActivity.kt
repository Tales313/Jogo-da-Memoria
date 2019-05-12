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

class ListagemActivity : AppCompatActivity() {
    private lateinit var listagemLvProfessores: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        listagemLvProfessores = findViewById(R.id.listagemLvProfessores)
        listagemLvProfessores.adapter = ListExampleAdapter(this@ListagemActivity)
    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        internal var sList = arrayOf(
            "Alana Marques de Morais",
            "Alex Sandro da Cunha Rego",
            "Candido José Ramos do Egypto",
            "Crishane Azevedo Freire",
            "Damires Yluska de Souza Fernandes",
            "Denio Mariz Timoteo de Sousa",
            "Edemberg Rocha da Silva",
            "Fausto Veras Maranhão Ayres",
            "Francisco Dantas Nobre Neto",
            "Francisdo Petrônio Alencar de Medeiros"
        )
        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return sList.size
        }

        override fun getItem(position: Int): Any {
            return sList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.activity_listagem, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.label.text = sList[position]
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row?.findViewById(R.id.label) as TextView
        }
    }
}
