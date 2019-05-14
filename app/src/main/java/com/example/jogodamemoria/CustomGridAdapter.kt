package com.example.jogodamemoria

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CustomGridAdapter(private val context: Context, private val thumbIds: Array<Int>) : BaseAdapter() {

    override fun getCount(): Int {
        return thumbIds.size
    }

    override fun getItem(position: Int): Any {
        return thumbIds[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            // imageView.layoutParams = GridView.LayoutParams(244, 373)
            imageView.setLayoutParams(GridView@ AbsListView.LayoutParams(244,373))
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(thumbIds[position])
        return imageView
    }
}
