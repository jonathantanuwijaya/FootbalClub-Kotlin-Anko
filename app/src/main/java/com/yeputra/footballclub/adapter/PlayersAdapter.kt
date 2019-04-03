package com.yeputra.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseRecyclerViewAdapter
import com.yeputra.footballclub.model.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*

class PlayersAdapter(
    item: MutableList<Player>,
    private val listener: (Player) -> Unit
) : BaseRecyclerViewAdapter<PlayersAdapter.VHolder, Player>(item) {

    override fun onBindViewHolder(holder: VHolder, item: Player, position: Int) {
        holder.binding(item, listener)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): VHolder
        = VHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_team, viewGroup, false)
    )

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(data: Player, listener: (Player) -> Unit) {
            Glide.with(containerView.context)
                .load(data.photo)
                .apply(RequestOptions().placeholder(R.drawable.ic_player))
                .into(img_logo)
            tv_title.text = data.name
            containerView.setOnClickListener {
                listener(data)
            }
        }
    }
}