package me.yusufeka.kadeyusuf.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import me.yusufeka.kadeyusuf.views.LeagueDetailActivity
import me.yusufeka.kadeyusuf.views.LeagueUi
import me.yusufeka.kadeyusuf.models.League
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class RecyclerViewAdapter(private val leagues: List<League>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LeagueUi().createView(
                AnkoContext.create(parent.context, parent)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(leagues[position])

    override fun getItemCount(): Int = leagues.size


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private var leagueName: TextView = itemView.find(LeagueUi.leagueName)
        private var leagueImage: ImageView = itemView.find(LeagueUi.leagueImage)

        fun bindItem(league: League) {
            leagueName.text = league.name
            league.image?.let { Picasso.get().load(it).fit().into(leagueImage) }
            containerView.setOnClickListener {
                containerView.context.startActivity<LeagueDetailActivity>("league" to league)
            }
        }
    }
}