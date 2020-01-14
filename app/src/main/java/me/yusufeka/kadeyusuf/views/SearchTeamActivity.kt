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
import kotlinx.android.synthetic.main.activity_search_team.*
import kotlinx.android.synthetic.main.content_search_team.*
import me.yusufeka.kadeyusuf.EspressoIdlingResource
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.adapters.TeamAdapter
import me.yusufeka.kadeyusuf.factories.SearchTeamFactory
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.viewmodels.SearchTeamViewModel

class SearchTeamActivity : AppCompatActivity() {

    companion object{
        const val SEARCH_TEAM_LEAGUE_ID_KEY = "leagueId"
    }

    private lateinit var searchTeamViewModel: SearchTeamViewModel

    private val adapter: TeamAdapter = TeamAdapter()

    private var leagueId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        leagueId = intent.getIntExtra(SearchMatchActivity.SEARCH_MATCH_LEAGUE_ID_KEY,0)

        val factory = SearchTeamFactory(TeamRepository())
        searchTeamViewModel = ViewModelProviders.of(this,factory).get(SearchTeamViewModel::class.java)
        searchTeamViewModel.getTeams().observe(this, Observer {
            adapter.teams = it
        })
        searchTeamViewModel.isLoading().observe(this, Observer {
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
        searchTeamViewModel.getInfoMessage().observe(this, Observer {
            if (it != ""){
                Toasty.info(this@SearchTeamActivity, it, Toast.LENGTH_SHORT, true).show()
            }
        })
        rvTeams.layoutManager = LinearLayoutManager(this@SearchTeamActivity)
        rvTeams.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search Team"
        searchView.setOnQueryTextListener(searchViewOnQueryTextListener)

        searchItem.setOnActionExpandListener(searchItemOnActionExpandListener)
        searchItem.expandActionView()
        return super.onCreateOptionsMenu(menu)
    }

    private val searchViewOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            adapter.clear()
            searchTeamViewModel.searchTeam(s,leagueId)
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
