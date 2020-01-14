package me.yusufeka.kadeyusuf.views


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_league_table.*
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.adapters.TableAdapter
import me.yusufeka.kadeyusuf.factories.TableFactory
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.viewmodels.TableViewModel


class LeagueTableFragment(
    private val leagueId: Int
) : Fragment() {

    private val tableAdapter = TableAdapter()

    private lateinit var tableViewModel: TableViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = TableFactory(LeagueRepository())
        tableViewModel = ViewModelProviders.of(this, factory).get(TableViewModel::class.java)
        rvTable.layoutManager = LinearLayoutManager(context)
        rvTable.adapter = tableAdapter

        tableViewModel.getTable().observe(this, Observer {
            tableAdapter.table = it
        })

        tableViewModel.isLoading().observe(this, Observer {
            if (it) {
                progressAnimationView.visibility = View.VISIBLE
                progressAnimationView.playAnimation()
            } else {
                progressAnimationView.cancelAnimation()
                progressAnimationView.visibility = View.INVISIBLE
            }
        })

        tableViewModel.updateTable(leagueId)

    }

}
