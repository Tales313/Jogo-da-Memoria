package com.example.jogodamemoria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class JogoActivity : AppCompatActivity() {

    private lateinit var jogoGv: GridView
    private var cartaSelecionada: ImageView? = null
    private var pontos = 0
    private val professores = intArrayOf(
        R.drawable.alana,
        R.drawable.alex,
        R.drawable.candido,
        R.drawable.crishane,
        R.drawable.damires,
        R.drawable.denio,
        R.drawable.edemberg,
        R.drawable.fausto
    )
    private var cartas = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7).toList().shuffled()
    private var posCartaSelecionada = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        this.jogoGv = findViewById(R.id.jogoGv)
        val imageAdapter = ImageAdapter(this@JogoActivity)
        this.jogoGv.adapter = imageAdapter
        this.jogoGv.setOnItemClickListener(Jogo())

    }

    inner class Jogo : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (posCartaSelecionada < 0) {
                posCartaSelecionada = position
                cartaSelecionada = view as ImageView
                view.setImageResource(professores[cartas[position]])
            } else {
                if (posCartaSelecionada == position) {
                    (view as ImageView).setImageResource(R.drawable.unknown)
                    Toast.makeText(this@JogoActivity, "Pick another card!", Toast.LENGTH_SHORT).show()
                }
                else if (cartas[posCartaSelecionada] != cartas[position]) {
                    // (view as ImageView).setImageResource(professores[pos[position]])
                    // Thread.sleep(1000)
                    cartaSelecionada?.setImageResource(R.drawable.unknown)
                    Toast.makeText(this@JogoActivity, "Erooouuu!", Toast.LENGTH_SHORT).show()
                } else {
                    (view as ImageView).setImageResource(professores[cartas[position]])
                    pontos++

                    if (pontos == 8) {
                        Toast.makeText(this@JogoActivity, "You win!", Toast.LENGTH_LONG).show()
                    }
                }
                posCartaSelecionada = -1
            }
        }
    }
}

