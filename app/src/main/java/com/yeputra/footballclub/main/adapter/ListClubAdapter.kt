package com.yeputra.footballclub.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yeputra.footballclub.main.model.Club
import com.yeputra.footballclub.main.ui.UIItemsClub
import org.jetbrains.anko.AnkoContext


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:22
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class ListClubAdapter(
    val items: MutableList<Club>,
    val listeter: (Club)-> Unit
): RecyclerView.Adapter<ListClubAdapter.VHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            UIItemsClub().createView(AnkoContext.create(parent.context, parent))
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(items[position],listeter)

    class VHolder(val v: View)
        : RecyclerView.ViewHolder(v) {

        var imgLogo: ImageView = v.findViewById(UIItemsClub.img_logo_club)
        var tvName : TextView = v.findViewById(UIItemsClub.tv_name)

        fun binding(club: Club, listener: (Club) -> Unit){
            tvName.text = club.name
            Glide.with(v.context).load(club.icon).into(imgLogo)

            v.setOnClickListener { listener(club) }
        }
    }
}