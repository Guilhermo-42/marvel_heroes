package com.guilhermo.marvelheroes.flow.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guilhermo.marvelheroes.R
import kotlinx.android.synthetic.main.hero_list_item.view.*

class HeroListAdapter(
    private val listener: OnHeroClickListener
) : RecyclerView.Adapter<HeroListAdapter.HeroListViewHolder>() {

    private var heroes: MutableList<HeroListPresenter.HeroListItemPresenter> = mutableListOf()

    private lateinit var context: Context

    fun updateHeroes(newHeroes: List<HeroListPresenter.HeroListItemPresenter>) {
        heroes.clear()
        heroes.addAll(newHeroes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        context = parent.context
        return HeroListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.hero_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        val hero = heroes[position]

        holder.apply {
            heroTitle.text = hero.heroName

            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            hero.heroImagePath?.let {
                Glide.with(context)
                    .load(it)
                    .placeholder(circularProgressDrawable)
                    .into(heroImage)
            }

            heroDescription.text = hero.heroDescription
            heroDetailsButton.setOnClickListener {
                listener.onItemClicked(hero.heroId)
            }
        }
    }

    inner class HeroListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heroTitle: TextView = itemView.heroItemTitleText
        val heroImage: ImageView = itemView.heroItemPosterImage
        val heroDescription: TextView = itemView.heroItemDescriptionText
        val heroDetailsButton: TextView = itemView.heroItemDetailsText
    }

    interface OnHeroClickListener {

        fun onItemClicked(heroId: String)

    }

}