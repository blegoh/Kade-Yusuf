package me.yusufeka.kadeyusuf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.views.TeamDetailActivity
import org.jetbrains.anko.startActivity

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    var teams: List<Team> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        teams = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_team,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(team: Team) {
            teamName.text = team.strTeam
            Picasso.get()
                .load(team.strTeamBadge)
                .fit()
                .into(teamLogo)
            containerView.setOnClickListener {
                containerView.context.startActivity<TeamDetailActivity>(TeamDetailActivity.TEAM_DETAIL_EVENT_KEY to team)
            }
        }
    }
}