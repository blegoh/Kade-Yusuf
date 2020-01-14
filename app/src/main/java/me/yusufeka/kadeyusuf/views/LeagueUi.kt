package me.yusufeka.kadeyusuf.views

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

class LeagueUi : AnkoComponent<ViewGroup> {

    companion object {
        const val leagueImage = 1
        const val leagueName = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        constraintLayout {
            imageView {
                id = leagueImage
            }.lparams(width = dip(50), height = dip(50))

            textView {
                id = leagueName
            }.lparams(wrapContent, wrapContent)

            applyConstraintSet {
                leagueImage {
                    connect(
                        TOP to TOP of PARENT_ID margin dip(16),
                        START to START of PARENT_ID margin dip(0),
                        END to END of PARENT_ID margin dip(0)
                    )
                }
                leagueName {
                    connect(
                        TOP to BOTTOM of leagueImage margin dip(8),
                        START to START of PARENT_ID margin dip(0),
                        END to END of PARENT_ID margin dip(0),
                        BOTTOM to BOTTOM of PARENT_ID margin dip(16)
                    )
                }
            }
        }
    }
}