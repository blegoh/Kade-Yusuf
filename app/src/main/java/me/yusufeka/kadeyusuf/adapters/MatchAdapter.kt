package me.yusufeka.kadeyusuf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.*
import me.yusufeka.kadeyusuf.views.MatchDetailActivity
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.models.Event
import org.jetbrains.anko.startActivity

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    var matchList: List<Event> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        matchList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_match,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(matchList[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(event: Event) {
            home.text = event.strHomeTeam
            away.text = event.strAwayTeam
            date.text = event.dateEvent
            val homeScore = event.intHomeScore ?: "-"
            val awayScore = event.intAwayScore ?: "-"
            score.text = String.format(
                containerView.context.resources.getString(R.string.score),
                homeScore,
                awayScore
            )
            containerView.setOnClickListener {
                containerView.context.startActivity<MatchDetailActivity>(MatchDetailActivity.MATCH_DETAIL_EVENT_KEY to event)
            }
        }
    }
}