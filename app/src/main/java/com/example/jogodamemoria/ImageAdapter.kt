package com.example.jogodamemoria

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return 16
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(this.context)
            var w = context.resources.displayMetrics.widthPixels / 4
            var h = w + (w/4)
            imageView.setLayoutParams(AbsListView.LayoutParams(w, h))
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            // imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else
            imageView = convertView as ImageView

        imageView.setImageResource(R.drawable.unknown)
        return imageView
    }
}
