package me.yusufeka.kadeyusuf.views

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.content_match_detail.*
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.factories.MatchDetailFactory
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.viewmodels.MatchDetailViewModel

class MatchDetailActivity : AppCompatActivity() {

    companion object {
        const val MATCH_DETAIL_EVENT_KEY = "event"
    }

    private lateinit var matchDetailViewModel: MatchDetailViewModel

    private var match: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white)
        )
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)

        val matchRepository = MatchRepository()
        matchRepository.database =
            MyDatabaseOpenHelper(this)
        val factory = MatchDetailFactory(TeamRepository(), matchRepository)
        matchDetailViewModel =
            ViewModelProviders.of(this, factory).get(MatchDetailViewModel::class.java)

        matchDetailViewModel.getHomeTeam().observe(this, Observer {
            Picasso.get()
                .load(it.strTeamBadge)
                .fit()
                .into(homeImage)
        })

        matchDetailViewModel.getAwayTeam().observe(this, Observer {
            Picasso.get()
                .load(it.strTeamBadge)
                .fit()
                .into(awayImage)
        })

        match = intent.getParcelableExtra("event")
        match?.let { event ->
            matchDetailViewModel.match = event
            matchDetailViewModel.check()
            showEvent(event)
        }

    }

    private fun showEvent(event: Event) {
        collapsingToolbarLayout.title = event.strEvent
        homeTeam.text = event.strHomeTeam
        awayTeam.text = event.strAwayTeam
        dateEvent.text = event.dateEvent
        val homeScore = event.intHomeScore ?: "-"
        val awayScore = event.intAwayScore ?: "-"
        score.text = "$homeScore : $awayScore"
        matchDetailViewModel.updateHomeTeam(event.idHomeTeam.toInt())
        matchDetailViewModel.updateAwayTeam(event.idAwayTeam.toInt())
        event.strHomeGoalDetails?.let { goals ->
            homeGoals.text = goals.replace(";", "\n")
        }
        event.strAwayGoalDetails?.let { goals ->
            awayGoals.text = goals.replace(";", "\n")
        }
        event.strHomeYellowCards?.let { cards ->
            homeYellowCards.text = cards.replace(";", "\n")
        }
        event.strAwayYellowCards?.let { cards ->
            awayYellowCards.text = cards.replace(";", "\n")
        }
        event.strHomeRedCards?.let { cards ->
            homeRedCards.text = cards.replace(";", "\n")
        }
        event.strAwayRedCards?.let { cards ->
            awayRedCards.text = cards.replace(";", "\n")
        }
        event.strHomeLineupGoalkeeper?.let { players ->
            homeGK.text = players.replace(";", "\n")
        }
        event.strAwayLineupGoalkeeper?.let { players ->
            awayGK.text = players.replace(";", "\n")
        }
        event.strHomeLineupDefense?.let { players ->
            homeDef.text = players.replace(";", "\n")
        }
        event.strAwayLineupDefense?.let { players ->
            awayDef.text = players.replace(";", "\n")
        }
        event.strHomeLineupMidfield?.let { players ->
            homeMid.text = players.replace(";", "\n")
        }
        event.strAwayLineupMidfield?.let { players ->
            awayMid.text = players.replace(";", "\n")
        }
        event.strHomeLineupForward?.let { players ->
            homeForward.text = players.replace(";", "\n")
        }
        event.strAwayLineupForward?.let { players ->
            awayForward.text = players.replace(";", "\n")
        }
        event.strHomeLineupSubstitutes?.let { players ->
            homeSubstitutes.text = players.replace(";", "\n")
        }
        event.strAwayLineupSubstitutes?.let { players ->
            awaySubstitutes.text = players.replace(";", "\n")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_match, menu)
        matchDetailViewModel.isFavorited().observe(this, Observer {
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
                if (matchDetailViewModel.isFavorited().value == true)
                    matchDetailViewModel.removeFromFavorite()
                else
                    matchDetailViewModel.addToFavorit()
            }
        }
        return true
    }

}
