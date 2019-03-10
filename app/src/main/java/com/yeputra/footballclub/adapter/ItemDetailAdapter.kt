package com.yeputra.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mlpt.siemo.mobilebanking.base.adapter.BaseRecyclerViewAdapter
import com.yeputra.footballclub.R
import com.yeputra.footballclub.model.Item
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_detail.*

class ItemDetailAdapter(
    items: MutableList<Item>
): BaseRecyclerViewAdapter<ItemDetailAdapter.VHolder, Item>(items) {

    override fun onBindViewHolder(holder: VHolder, item: Item) =
        holder.binding(item)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VHolder =
            VHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_match_detail, p0,false))

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(detail: Item) {
            tv_title.text = detail.title
            tv_home_detail.text = parsing(detail.home)
            tv_away_detail.text = parsing(detail.away)
        }

        fun parsing(data: String?): String =
            data?.let {
                it.replace(";","\n").trim()
            }?: run{""}
    }

}