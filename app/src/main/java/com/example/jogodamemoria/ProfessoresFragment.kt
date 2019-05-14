package com.example.jogodamemoria

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

class ProfessoresFragment : Fragment() {

    private val images = arrayOf(
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred,
        R.drawable.fred
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_jogo, container, false)
        val gridView = view.findViewById<View>(R.id.jogoGl) as GridView
        gridView.adapter = CustomGridAdapter(activity!!, images)
        return view
    }
}
