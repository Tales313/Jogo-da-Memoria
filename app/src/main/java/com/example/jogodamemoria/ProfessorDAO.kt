package com.example.jogodamemoria

import android.content.ContentValues
import android.content.Context
import java.io.Serializable

class ProfessorDAO: Serializable {
    private lateinit var banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    fun insert(p: Professor) {
        val cv = ContentValues()
        cv.put("nome", p.nome)
        this.banco.writableDatabase.insert("pessoa", null, cv)
    }

    fun get(): ArrayList<Professor> {
        val colunas = arrayOf("id", "nome")
        val lista = ArrayList<Professor>()

        val c = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, "nome")

        c.moveToFirst()

        if(c.count > 0) {
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val nome = c.getString(c.getColumnIndex("nome"))
                lista.add(Professor(id, nome))
            }while(c.moveToNext())
        }
        c.close()
        return lista
    }

    fun get(index: Int): Professor? {
        val colunas = arrayOf("id", "nome")
        val where = "id = ?"
        val pwhere = arrayOf(index.toString())

        val c = this.banco.readableDatabase.query("pessoa", colunas, where, pwhere, null, null, null)

        c.moveToFirst()

        if(c.count > 0) {
            val id = c.getInt(c.getColumnIndex("id"))
            val nome = c.getString(c.getColumnIndex("nome"))
            c.close()
            return Professor(id, nome)
        }
        c.close()
        return null
    }

    fun update(p: Professor) {
        val where = "id = ?"
        val pwhere = arrayOf(p.id.toString())
        val cv = ContentValues()
        cv.put("id", p.id)
        cv.put("nome", p.nome)

        this.banco.writableDatabase.update("pessoa", cv, where, pwhere)
    }

    fun delete(id: Int) {
        val where = "id = ?"
        val pwhere = arrayOf(id.toString())

        this.banco.writableDatabase.delete("pessoa", where, pwhere)
    }
}