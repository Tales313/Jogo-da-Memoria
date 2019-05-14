package com.example.jogodamemoria

import android.content.ContentValues
import android.content.Context

class JogadorDAO {
    private lateinit var banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    fun insert(j: Jogador) {
        val cv = ContentValues()
        cv.put("nome", j.nome)
        this.banco.writableDatabase.insert("pessoa", null, cv)
    }

    fun get(): ArrayList<Jogador> {
        val colunas = arrayOf("id", "nome", "vitorias")
        val lista = ArrayList<Jogador>()

        val c = this.banco.readableDatabase.query("jogador", colunas, null, null, null, null, "nome")

        c.moveToFirst()

        if(c.count > 0) {
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val nome = c.getString(c.getColumnIndex("nome"))
                lista.add(Jogador(id, nome))
            }while(c.moveToNext())
        }
        c.close()
        return lista
    }

    fun get(index: Int): Jogador? {
        val colunas = arrayOf("id", "nome", "vitorias")
        val where = "id = ?"
        val pwhere = arrayOf(index.toString())

        val c = this.banco.readableDatabase.query("jogador", colunas, where, pwhere, null, null, null)

        c.moveToFirst()

        if(c.count > 0) {
            val id = c.getInt(c.getColumnIndex("id"))
            val nome = c.getString(c.getColumnIndex("nome"))
            val vitorias = c.getInt(c.getColumnIndex("vitorias"))
            c.close()
            var j = Jogador(id, nome)
            j.vitorias = vitorias
            return j
        }
        c.close()
        return null
    }

    fun update(j: Jogador) {
        val where = "id = ?"
        val pwhere = arrayOf(j.id.toString())
        val cv = ContentValues()
        cv.put("id", j.id)
        cv.put("nome", j.nome)
        cv.put("vitorias", j.vitorias)

        this.banco.writableDatabase.update("jogador", cv, where, pwhere)
    }

    fun delete(id: Int) {
        val where = "id = ?"
        val pwhere = arrayOf(id.toString())

        this.banco.writableDatabase.delete("jogador", where, pwhere)
    }
}