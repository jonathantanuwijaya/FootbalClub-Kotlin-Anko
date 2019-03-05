package com.yeputra.footballclub.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.main.model.Club
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_match.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:22
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class ListClubAdapter(
    private val items: MutableList<Club>,
    private val listener: (Club)-> Unit
): RecyclerView.Adapter<ListClubAdapter.VHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_match,parent,false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(items[position],listener)

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(club: Club, listener: (Club) -> Unit){
            tv_date.text = club.name
            containerView.setOnClickListener { listener(club) }
        }
    }
}