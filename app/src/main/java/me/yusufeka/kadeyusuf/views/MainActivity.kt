package me.yusufeka.kadeyusuf.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.yusufeka.kadeyusuf.R
import me.yusufeka.kadeyusuf.R.array
import me.yusufeka.kadeyusuf.adapters.RecyclerViewAdapter
import me.yusufeka.kadeyusuf.models.League
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.dip
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<League> = mutableListOf()
    lateinit var mainUI : MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = MainActivityUI()
        mainUI.setContentView(this)

        initData()

        mainUI.rv.layoutManager = GridLayoutManager(this, 2)
        mainUI.rv.adapter = RecyclerViewAdapter(items)
    }

    private fun initData() {
        val id = resources.getStringArray(array.league_id)
        val name = resources.getStringArray(array.league_name)
        val image = resources.obtainTypedArray(array.league_image)
        val desc = resources.getStringArray(array.league_desc)
        items.clear()
        for (i in name.indices) {
            items.add(
                League(
                    id[i].toInt(),
                    name[i],
                    image.getResourceId(i, 0),
                    desc[i]
                )
            )
        }

        image.recycle()
    }

    class MainActivityUI : AnkoComponent<MainActivity> {

        lateinit var rv: RecyclerView

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            constraintLayout {
                rv = recyclerView {
                    id = R.id.rv
                }.lparams(matchConstraint, matchConstraint)

                applyConstraintSet {
                    rv {

                        connect(
                            START to START of PARENT_ID margin dip(0),
                            TOP to TOP of PARENT_ID margin dip(0),
                            END to END of PARENT_ID margin dip(0),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(0)
                        )
                    }
                }
            }
        }
    }
}
