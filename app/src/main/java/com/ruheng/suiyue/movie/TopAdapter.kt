package com.ruheng.suiyue.movie

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.SubjectsItem
import me.zhanghai.android.materialratingbar.MaterialRatingBar

/**
 * Created by lvruheng on 2018/3/6.
 */
class TopAdapter(context: Context, movieList: MutableList<SubjectsItem>?) : RecyclerView.Adapter<TopAdapter.TopViewHolder>() {
    var context: Context? = context
    var list: MutableList<SubjectsItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = movieList
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.title
        val imgUrl = bean?.images?.small
        var rating = bean?.rating?.average
        val genres = bean?.genres
        val year = bean?.year
        var genreStr = "$year"
        val casts = bean?.casts
        var castStr = ""
        casts!!.indices
                .asSequence()
                .takeWhile { it <= 2 }
                .forEach { castStr = castStr + " / " + casts[it].name }
        castStr = castStr.substring(3)
        genres!!.indices
                .asSequence()
                .takeWhile { it <= 2 }
                .forEach { genreStr = genreStr + " / " + genres[it] }
        holder.tv_title?.text = title
        holder.sv_photo?.setImageURI(imgUrl)
        holder?.rt_rating?.rating = rating?.toFloat()!! / 2
        holder?.tv_rating?.text = rating.toString()
        holder?.tv_geners?.text = genreStr
        holder?.tv_casts?.text = castStr
        holder?.tv_rank?.text = (position+1).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(inflater?.inflate(R.layout.item_top_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class TopViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_rating: TextView? = null
        var rt_rating: MaterialRatingBar? = null
        var tv_geners: TextView? = null
        var tv_casts: TextView? = null
        var tv_rank: TextView? = null

        init {
            sv_photo = itemView?.findViewById(R.id.sv_top_photo)
            tv_title = itemView?.findViewById(R.id.tv_top_title)
            tv_rating = itemView?.findViewById(R.id.tv_top_rating)
            rt_rating = itemView?.findViewById(R.id.rt_top_rating)
            tv_geners = itemView?.findViewById(R.id.tv_top_geners)
            tv_casts = itemView?.findViewById(R.id.tv_top_casts)
            tv_rank = itemView?.findViewById(R.id.tv_top_rank)
        }
    }
}