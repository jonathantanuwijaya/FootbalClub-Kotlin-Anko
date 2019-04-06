package com.yeputra.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseRecyclerViewAdapter
import com.yeputra.footballclub.model.League
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league.*

class LeagueAdapter(
    item: MutableList<League>,
    private val listener: (League) -> Unit
) : BaseRecyclerViewAdapter<LeagueAdapter.VHolder, League>(item) {

    override fun onBindViewHolder(holder: VHolder, item: League, position: Int) {
        holder.binding(item, listener)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): VHolder
            = VHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_league, viewGroup, false)
    )

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(data: League, listener: (League) -> Unit) {
            tv_name.text = data.name
            containerView.setOnClickListener {
                listener(data)
            }
        }
    }
}