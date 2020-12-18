package com.kodless.kotlinnews.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodless.kotlinnews.R
import com.kodless.kotlinnews.databinding.TekSatirBinding
import com.kodless.kotlinnews.model.Article
import com.kodless.kotlinnews.view.MainActivity2
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main2.view.*
import kotlinx.android.synthetic.main.tek_satir.view.*

class RecylerAdapter(val haberListem: List<Article>) :
    RecyclerView.Adapter<RecylerAdapter.CustomViewHolder>(), NewsClickListener {


    class CustomViewHolder(var view: TekSatirBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<TekSatirBinding>(inflater, R.layout.tek_satir, parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.news = haberListem[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return haberListem.size
    }

    override fun onNewsClick(v:View) {
        val uuid = v.tvUUid.text.toString().toInt()
        println("click tıklandı")
        val intent = Intent(v.context,MainActivity2::class.java)
        intent.putExtra("veri",uuid)
        v.context.startActivity(intent)
    }
}