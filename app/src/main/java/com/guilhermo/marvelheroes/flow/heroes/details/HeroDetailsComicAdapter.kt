package com.guilhermo.marvelheroes.flow.heroes.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.guilhermo.marvelheroes.R
import kotlinx.android.synthetic.main.hero_comic_list_item.view.*

class HeroDetailsComicAdapter :
    RecyclerView.Adapter<HeroDetailsComicAdapter.HeroDetailsComicViewHolder>() {

    private val comicsPresenter: MutableList<HeroComicsPresenter.ComicPresenter> by lazy {
        mutableListOf<HeroComicsPresenter.ComicPresenter>()
    }

    private lateinit var context: Context

    fun updateList(newComics: List<HeroComicsPresenter.ComicPresenter>) {
        comicsPresenter.clear()
        comicsPresenter.addAll(newComics)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroDetailsComicViewHolder {
        context = parent.context
        return HeroDetailsComicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hero_comic_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = comicsPresenter.size

    override fun onBindViewHolder(holder: HeroDetailsComicViewHolder, position: Int) {
        val comic = comicsPresenter[position]

        holder.apply {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context)
                .load(comic.imageUrl)
                .placeholder(circularProgressDrawable)
                .into(comicImage)

            comicText.text = comic.name
        }
    }

    inner class HeroDetailsComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comicImage: ImageView = itemView.comicListItemImage
        val comicText: TextView = itemView.comicListItemName
    }

}