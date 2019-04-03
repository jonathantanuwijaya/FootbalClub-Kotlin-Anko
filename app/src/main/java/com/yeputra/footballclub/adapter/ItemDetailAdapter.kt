package com.yeputra.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseRecyclerViewAdapter
import com.yeputra.footballclub.model.Item
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_detail.*

class ItemDetailAdapter(
    items: MutableList<Item>
): BaseRecyclerViewAdapter<ItemDetailAdapter.VHolder, Item>(items) {

    override fun onBindViewHolder(holder: VHolder, item: Item, position: Int) =
        holder.binding(item)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): VHolder =
            VHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_match_detail, viewGroup,false))

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(detail: Item) {
            tv_title.text = detail.title
            tv_home_detail.text = parsing(detail.home)
            tv_away_detail.text = parsing(detail.away)

        }

        private fun parsing(data: String?): String =
            data?.replace(";","\n")?.trim() ?: run{"-"}
    }

}