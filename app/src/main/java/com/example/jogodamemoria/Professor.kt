package com.example.jogodamemoria

import java.io.Serializable

class Professor: Serializable {

    var nome: String
    var disciplina: String
    var foto: Int

    constructor(nome: String, disciplina: String, foto: Int) {
        this.nome = nome
        this.disciplina = disciplina
        this.foto = foto
    }

    override fun toString(): String {
        return "Professor(nome='$nome', disciplina='$disciplina', foto=$foto)"
    }

}