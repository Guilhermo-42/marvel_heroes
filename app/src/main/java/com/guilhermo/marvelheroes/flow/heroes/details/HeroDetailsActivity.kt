package com.guilhermo.marvelheroes.flow.heroes.details

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.guilhermo.marvelheroes.R
import kotlinx.android.synthetic.main.activity_hero_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailsActivity : AppCompatActivity() {

    companion object {
        const val HERO_ID = "hero_id"
    }

    private val viewModel by viewModel<HeroDetailsViewModel>()

    private val adapter: HeroDetailsComicAdapter by lazy {
        HeroDetailsComicAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)
        setupViews()

        viewModel.livePresenter.observe(this, Observer {
            updateUi(it)
        })

        viewModel.liveComicsPresenter.observe(this, Observer {
            updateComicsUi(it)
        })

        retrieveData()
    }

    private fun setupViews() {
        setSupportActionBar(heroDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        heroComicsList.adapter = adapter
    }

    private fun retrieveData() {
        val heroId = intent.getStringExtra(HERO_ID) ?: return

        viewModel.retrieveHeroDetails(heroId)
    }

    private fun updateUi(presenter: HeroDetailsPresenter) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this)
            .load(presenter.heroPosterPath)
            .placeholder(circularProgressDrawable)
            .into(heroDetailsPosterImage)

        heroDetailsTitle.text = presenter.heroName
        heroDetailsText.text = presenter.heroDescription
    }

    private fun updateComicsUi(presenter: HeroComicsPresenter) {
        heroComicsProgressBar.visibility = View.GONE

        if (presenter.comics.isNullOrEmpty()) {
            heroComicsEmptyState.visibility = View.VISIBLE
            heroComicsList.visibility = View.GONE
        } else {
            heroComicsEmptyState.visibility = View.GONE
            heroComicsList.visibility = View.VISIBLE
            adapter.updateList(presenter.comics)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
