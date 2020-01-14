package me.yusufeka.kadeyusuf.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.content_search_match.*
import me.yusufeka.kadeyusuf.EspressoIdlingResource
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.adapters.MatchAdapter
import me.yusufeka.kadeyusuf.factories.SearchMatchFactory
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.viewmodels.SearchMatchViewModel

class SearchMatchActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_MATCH_LEAGUE_ID_KEY = "leagueId"
    }

    private lateinit var searchMatchViewModel: SearchMatchViewModel

    private val adapter: MatchAdapter = MatchAdapter()

    private var leagueId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        leagueId = intent.getIntExtra(SEARCH_MATCH_LEAGUE_ID_KEY, 0)

        val factory = SearchMatchFactory(MatchRepository())
        searchMatchViewModel =
            ViewModelProviders.of(this, factory).get(SearchMatchViewModel::class.java)
        searchMatchViewModel.getMatch().observe(this, Observer {
            adapter.matchList = it
        })
        searchMatchViewModel.isLoading().observe(this, Observer {
            if (it) {
                progressAnimationView.visibility = View.VISIBLE
                progressAnimationView.playAnimation()
                EspressoIdlingResource.increment()
            } else {
                progressAnimationView.cancelAnimation()
                progressAnimationView.visibility = View.INVISIBLE
                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        searchMatchViewModel.getInfoMessage().observe(this, Observer {
            if (it != "") {
                Toasty.info(this@SearchMatchActivity, it, Toast.LENGTH_SHORT, true).show()
            }
        })
        rvMatch.layoutManager = LinearLayoutManager(this@SearchMatchActivity)
        rvMatch.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search Match"
        searchView.setOnQueryTextListener(searchViewOnQueryTextListener)

        searchItem.setOnActionExpandListener(searchItemOnActionExpandListener)
        searchItem.expandActionView()
        return super.onCreateOptionsMenu(menu)
    }

    private val searchViewOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            adapter.clear()
            searchMatchViewModel.searchMatch(s, leagueId)
            return false
        }

        override fun onQueryTextChange(s: String): Boolean {
            return false
        }
    }

    private val searchItemOnActionExpandListener = object : MenuItem.OnActionExpandListener {

        override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
            finish()
            return true
        }

        override fun onMenuItemActionExpand(item: MenuItem): Boolean {
            return true
        }
    }
}
