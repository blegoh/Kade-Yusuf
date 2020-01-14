package me.yusufeka.kadeyusuf.views


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_team.*
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.adapters.TeamAdapter
import me.yusufeka.kadeyusuf.factories.TeamFactory
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import me.yusufeka.kadeyusuf.viewmodels.TeamViewModel

class TeamFragment(
    private val leagueId: Int
) : Fragment() {

    private val teamAdapter = TeamAdapter()

    private val favAdapter = TeamAdapter()

    private lateinit var teamViewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamRepository = TeamRepository()
        teamRepository.database = context?.let { MyDatabaseOpenHelper(it) }
        val factory = TeamFactory(teamRepository)
        teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel::class.java)
        rvTeam.layoutManager = GridLayoutManager(context,2)
        rvTeam.adapter = teamAdapter

        teamViewModel.getTeams().observe(this, Observer {
            teamAdapter.teams = it
        })

        teamViewModel.getFavTeams().observe(this, Observer {
            favAdapter.teams = it
        })

        teamViewModel.isLoading().observe(this, Observer {
            if (it) {
                progressAnimationView.visibility = View.VISIBLE
                progressAnimationView.playAnimation()
            } else {
                progressAnimationView.cancelAnimation()
                progressAnimationView.visibility = View.INVISIBLE
            }
        })

        teamViewModel.updateTeams(leagueId)
        teamViewModel.updateFavTeam(leagueId)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.list ->{
                    rvTeam.adapter = teamAdapter
                    true
                }
                R.id.fav -> {
                    rvTeam.adapter = favAdapter
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_league_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        teamViewModel.updateFavTeam(leagueId)
    }
}
