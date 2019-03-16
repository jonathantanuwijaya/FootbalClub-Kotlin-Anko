package com.yeputra.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseRecyclerViewAdapter
import com.yeputra.footballclub.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_match.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:22
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class MatchAdapter(
    items: MutableList<Event>,
    private val listener: (Event)-> Unit
): BaseRecyclerViewAdapter<MatchAdapter.VHolder, Event>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_match,parent,false)
        )

    override fun onBindViewHolder(holder: VHolder, item: Event) {
        holder.binding(item,listener)
    }

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(event: Event, listener: (Event) -> Unit){
            tv_date.text = event.getFormatDateEvent()
            tv_home_name.text = event.homeTeam
            tv_away_name.text = event.awayTeam

            event.homeScore?.let {
                tv_home_score.text = it
            }?: run{
                tv_home_score.text = "-"
            }

            event.awayScore?.let{
                tv_away_score.text = it
            }?: run{
                tv_away_score.text = "-"
            }
            containerView.setOnClickListener { listener(event) }
        }
    }
}