package com.example.jogodamemoria

class Jogador {

    var id: Int
    var nome: String
    var vitorias: Int

    constructor(id: Int, nome: String) {
        this.id = id
        this.nome = nome
        this.vitorias = 0
    }

    constructor(nome: String) {
        this.id = -1
        this.nome = nome
        this.vitorias = 0
    }

    fun addVitoria() {
        this.vitorias++
    }

    override fun toString(): String = "Jogador(id=$id, nome='$nome', vitorias=$vitorias)"

}