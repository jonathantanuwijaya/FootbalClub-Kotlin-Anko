package com.yeputra.footballclub.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseRecyclerViewAdapter
import com.yeputra.footballclub.model.Standing
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_standing.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:22
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class StandingsAdapter(
    items: MutableList<Standing>,
    private val listener: (Standing)-> Unit
): BaseRecyclerViewAdapter<StandingsAdapter.VHolder, Standing>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_standing,parent,false),
            listener
        )

    override fun onBindViewHolder(holder: VHolder, item: Standing, position: Int) {
        holder.binding(item, position)
    }

    class VHolder(override val containerView: View, private val listener: (Standing)-> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun binding(standing: Standing, position: Int){
            tv_number.text = (position+1).toString() + "."
            tv_team_name.text = standing.name
            tv_played.text = standing.played.toString()
            tv_win.text = standing.win.toString()
            tv_draw.text = standing.draw.toString()
            tv_loss.text = standing.loss.toString()
            tv_goalsdif.text = standing.goalsdifference.toString()
            tv_points.text = standing.total.toString()

            containerView.setOnClickListener { listener(standing) }
        }
    }
}