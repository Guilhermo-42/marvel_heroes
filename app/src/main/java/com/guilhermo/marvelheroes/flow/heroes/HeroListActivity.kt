package com.guilhermo.marvelheroes.flow.heroes

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.guilhermo.marvelheroes.R
import com.guilhermo.marvelheroes.extensions.goToActivityWithData
import com.guilhermo.marvelheroes.flow.heroes.details.HeroDetailsActivity
import com.guilhermo.marvelheroes.flow.heroes.details.HeroDetailsActivity.Companion.HERO_ID
import kotlinx.android.synthetic.main.activity_heroes_list.*
import kotlinx.android.synthetic.main.hero_list_main_state_toolbar.*
import kotlinx.android.synthetic.main.hero_list_search_state_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : AppCompatActivity(), HeroListAdapter.OnHeroClickListener {

    private val viewModel by viewModel<HeroListViewModel>()

    private val adapter: HeroListAdapter by lazy {
        HeroListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_list)
        setupViews()
        lifecycle.addObserver(viewModel)

        viewModel.liveHeroesPresenter.observe(this, Observer {
            updateUi(it)
        })
    }

    private fun setupViews() {
        heroesList.adapter = adapter
        heroListMainStateSearchButton.setOnClickListener {
            includeHeroListSearchState.visibility = View.VISIBLE
            includeHeroListMainState.visibility = View.GONE
        }
        heroListSearchExitButton.setOnClickListener {
            includeHeroListSearchState.visibility = View.GONE
            includeHeroListMainState.visibility = View.VISIBLE
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                heroListSearchTextInputLayout.windowToken, 0
            )
            heroListSearchTextInputEditText.setText("")
            viewModel.retrieveHeroes()
        }
        heroListSearchTextInputEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.onSearchClicked(heroListSearchTextInputEditText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun updateUi(presenter: HeroListPresenter) {
        adapter.updateHeroes(presenter.heroes)

        heroListProgressBar.visibility = View.GONE
        if (presenter.heroes.isEmpty()) {
            heroesListEmptyState.visibility = View.VISIBLE
            heroesList.visibility = View.GONE
        } else {
            heroesListEmptyState.visibility = View.GONE
            heroesList.visibility = View.VISIBLE
        }

    }

    override fun onItemClicked(heroId: String) {
        goToActivityWithData(HeroDetailsActivity::class.java, Bundle().apply {
            this.putString(HERO_ID, heroId)
        })
    }

}
