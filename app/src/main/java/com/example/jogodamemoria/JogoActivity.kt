package com.example.jogodamemoria

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {

    private lateinit var jogoGv: GridView
    private var primeiraCarta: ImageView? = null
    private var segundaCarta: ImageView? = null
    private var pontos = 0
    private var acertouUltimaJogada = false
    private lateinit var professores: ArrayList<Professor>
    private var cartas = arrayListOf<Int>() //= intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7).toList().shuffled()
    private var posCartaSelecionada = -1
    private lateinit var jogadorLogado: Jogador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        jogoGv = findViewById(R.id.jogoGv)
        val imageAdapter = ImageAdapter(this@JogoActivity)
        jogoGv.adapter = imageAdapter
        jogoGv.setOnItemClickListener(Jogo())
        jogadorLogado = intent.getSerializableExtra("JOGADOR") as Jogador
        professores = intent.getSerializableExtra("PROFESSORES") as ArrayList<Professor>

        var x: Int
        val arrayAux1 = arrayListOf<Int>()
        while(true) {
            x = Random.nextInt(0, 22)
            if(!arrayAux1.contains(x))
                arrayAux1.add(x)
            if(arrayAux1.size == 8)
                break
        }
        cartas.addAll(arrayAux1)
        cartas.addAll(arrayAux1)
        cartas = cartas.toList().shuffled() as ArrayList<Int>
    }

    inner class Jogo : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (posCartaSelecionada < 0) {
                if(!acertouUltimaJogada) {
                    primeiraCarta?.setImageResource(R.drawable.unknown)
                    segundaCarta?.setImageResource(R.drawable.unknown)
                    acertouUltimaJogada = false
                }
                posCartaSelecionada = position
                primeiraCarta = view as ImageView
                view.setImageResource(professores[cartas[position]].foto)
            } else {
                if (posCartaSelecionada == position) {
                    Toast.makeText(this@JogoActivity, "Escolha outra carta!", Toast.LENGTH_SHORT).show()
                }
                else if (cartas[posCartaSelecionada] != cartas[position]) {
                    (view as ImageView).setImageResource(professores[cartas[position]].foto)
                    posCartaSelecionada = -1
                    acertouUltimaJogada = false
                    segundaCarta = view
                } else {
                    (view as ImageView).setImageResource(professores[cartas[position]].foto)
                    pontos++
                    posCartaSelecionada = -1
                    acertouUltimaJogada = true
                    segundaCarta = view
                    primeiraCarta?.setOnClickListener(null)
                    segundaCarta?.setOnClickListener(null)
                    Toast.makeText(this@JogoActivity, "Acertou!", Toast.LENGTH_SHORT).show()

                    if (pontos == 8) {
                        jogadorLogado.addVitoria()
                        val intent = Intent()
                        intent.putExtra("JOGADOR", jogadorLogado)
                        setResult(Activity.RESULT_OK, intent)
                        val msg = "Mais uma vitória para ${jogadorLogado.nome}! Total de ${jogadorLogado.vitorias}."
                        Toast.makeText(this@JogoActivity, msg, Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }
        }
    }
}

