package me.yusufeka.kadeyusuf.views


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_match.*
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.adapters.MatchAdapter
import me.yusufeka.kadeyusuf.factories.MatchFactory
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.viewmodels.MatchViewModel
import org.jetbrains.anko.startActivity

class MatchFragment(
    private val leagueId: Int
) : Fragment() {

    private val adapterLast: MatchAdapter = MatchAdapter()
    private val adapterNext: MatchAdapter = MatchAdapter()
    private val adapterFav: MatchAdapter = MatchAdapter()

    private lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchRepository = MatchRepository()
        matchRepository.database = context?.let { MyDatabaseOpenHelper(it) }
        val factory = MatchFactory(matchRepository)
        matchViewModel = ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)
        rvMatch.layoutManager = LinearLayoutManager(context)
        rvMatch.adapter = adapterLast

        matchViewModel.getLastMatch().observe(this, Observer {
            adapterLast.matchList = it
        })

        matchViewModel.getNextMatch().observe(this, Observer {
            adapterNext.matchList = it
        })

        matchViewModel.getFavMatch().observe(this, Observer {
            adapterFav.matchList = it
        })

        matchViewModel.isLoading().observe(this, Observer {
            if (it) {
                progressAnimationView.visibility = View.VISIBLE
                progressAnimationView.playAnimation()
            } else {
                progressAnimationView.cancelAnimation()
                progressAnimationView.visibility = View.INVISIBLE
            }
        })

        matchViewModel.updateLastMatch(leagueId)
        matchViewModel.updateNextMatch(leagueId)
        matchViewModel.updateFavMatch(leagueId)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.last -> {
                    rvMatch.adapter = adapterLast
                    true
                }
                R.id.next -> {
                    rvMatch.adapter = adapterNext
                    true
                }
                R.id.fav -> {
                    rvMatch.adapter = adapterFav
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
        matchViewModel.updateFavMatch(leagueId)
    }
}
