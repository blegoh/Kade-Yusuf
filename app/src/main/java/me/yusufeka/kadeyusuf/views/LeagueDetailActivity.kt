package me.yusufeka.kadeyusuf.views

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*
import kotlinx.android.synthetic.main.content_league_detail.*
import me.yusufeka.kadeyusuf.PageAdapter
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.factories.LeagueFactory
import me.yusufeka.kadeyusuf.models.League
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.viewmodels.LeagueViewModel
import org.jetbrains.anko.startActivity


class LeagueDetailActivity : AppCompatActivity() {

    private lateinit var leagueViewModel: LeagueViewModel

    private var league: League? = null

    private lateinit var pageAdapter: PageAdapter

    private var selected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = LeagueFactory(LeagueRepository())
        leagueViewModel = ViewModelProviders.of(this, factory).get(LeagueViewModel::class.java)

        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white)
        )
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)

        leagueViewModel.getLeague().observe(this, Observer {
            leagueName.text = it.strLeague
            collapsingToolbarLayout.title = it.strLeague
            leagueDetail.text = it.strDescriptionEN
            leagueCountry.text = it.strCountry
            leagueFormedYear.text = it.intFormedYear
        })

        leagueViewModel.isLoading().observe(this, Observer {

        })

        league = intent.getParcelableExtra("league")
        league?.let {
            leagueViewModel.apply {
                updateLeague(it.id)
                pageAdapter = PageAdapter(
                    supportFragmentManager,
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
                )
                pageAdapter.pages = listOf(
                    MatchFragment(it.id),
                    LeagueTableFragment(it.id),
                    TeamFragment(it.id)
                )
                pageAdapter.titles = listOf("Matchs", "Table", "Teams")
                viewPager.adapter = pageAdapter
                tabs.setupWithViewPager(viewPager)
            }
            it.image?.let { image ->
                Picasso.get().load(image).fit().into(leagueImage)
            }
        }

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                selected = position
            }
        })

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_search -> {
                league?.let {
                    when (selected) {
                        0 -> startActivity<SearchMatchActivity>(SearchMatchActivity.SEARCH_MATCH_LEAGUE_ID_KEY to it.id)
                        2 -> startActivity<SearchTeamActivity>(SearchTeamActivity.SEARCH_TEAM_LEAGUE_ID_KEY to it.id)
                    }
                }
            }
        }
        return true
    }
}
