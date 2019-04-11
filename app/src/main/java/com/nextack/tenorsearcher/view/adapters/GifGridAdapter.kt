package com.nextack.tenorsearcher.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nextack.tenorsearcher.R
import kotlinx.android.synthetic.main.fragment_trending.*
import java.util.*

class GifGridAdapter(var context: Context) : BaseAdapter() {

    val urlList: MutableList<String> = LinkedList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        if(convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.grid_item, null)
        } else {
            view = convertView
        }

        val imageView = view.findViewById<ImageView>(R.id.grid_item_image)
        val spinnerView = view.findViewById<ProgressBar>(R.id.grid_item_spinner)

        Glide.with(context).load(urlList[position]).listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    spinnerView.visibility = View.INVISIBLE
                    imageView.visibility = View.INVISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    spinnerView.visibility = View.INVISIBLE
                    imageView.visibility = View.VISIBLE
                    return false
                }
            }
        ).into(imageView)

        return view
    }

    override fun getItem(position: Int): Any {
        return urlList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return urlList.size
    }

    fun update(urlList: List<String>?) {
        urlList?.let {
            this.urlList.clear()
            this.urlList.addAll(urlList)
        }
        this.notifyDataSetChanged()
    }

    fun add(urlList: List<String>?) {
        urlList?.let {
            this.urlList.addAll(urlList)
            this.notifyDataSetChanged()
        }
    }
}