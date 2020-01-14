package me.yusufeka.kadeyusuf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_table.*
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.models.Table

class TableAdapter : RecyclerView.Adapter<TableAdapter.ViewHolder>() {
    var table: List<Table> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        table = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_table,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = table.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(table[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(table: Table) {
            teamName.text = table.name
            play.text = table.played.toString()
            win.text = table.win.toString()
            draw.text = table.draw.toString()
            loss.text = table.loss.toString()
            f.text = table.goalsfor.toString()
            a.text = table.goalsagainst.toString()
            gd.text = table.goalsdifference.toString()
            pts.text = table.total.toString()
        }
    }
}