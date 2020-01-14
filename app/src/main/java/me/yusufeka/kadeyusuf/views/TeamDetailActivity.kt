package me.yusufeka.kadeyusuf.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.content_team_detail.*
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.factories.TeamDetailFactory
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import me.yusufeka.kadeyusuf.viewmodels.TeamDetailViewModel

class TeamDetailActivity : AppCompatActivity() {

    companion object {
        const val TEAM_DETAIL_EVENT_KEY = "team"
    }

    private lateinit var teamDetailViewModel: TeamDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white)
        )
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)

        val teamRepository = TeamRepository()
        teamRepository.database =
            MyDatabaseOpenHelper(this)
        val factory = TeamDetailFactory(teamRepository)
        teamDetailViewModel =
            ViewModelProviders.of(this, factory).get(TeamDetailViewModel::class.java)


        val team: Team? = intent.getParcelableExtra(TEAM_DETAIL_EVENT_KEY)
        team?.let {
            teamDetailViewModel.team = it
            teamDetailViewModel.check()
            Picasso.get()
                .load(team.strTeamBadge)
                .fit()
                .into(teamLogo)
            collapsingToolbarLayout.title = it.strTeam
            teamName.text = it.strTeam
            alternate.text = it.strAlternate
            formedYear.text = it.intFormedYear
            stadium.text = it.strStadium
            description.text = it.strDescriptionEN
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_match, menu)
        teamDetailViewModel.isFavorited().observe(this, Observer {
            if (it) {
                menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
            } else {
                menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.add_to_favorite -> {
                if (teamDetailViewModel.isFavorited().value == true)
                    teamDetailViewModel.removeFromFavorite()
                else
                    teamDetailViewModel.addToFavorit()
            }
        }
        return true
    }
}
