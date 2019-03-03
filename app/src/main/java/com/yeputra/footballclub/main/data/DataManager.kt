package com.yeputra.footballclub.main.data

import android.content.Context
import com.yeputra.footballclub.R
import com.yeputra.footballclub.main.model.Club


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 18:55
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class DataManager(val context: Context) {

    fun getClubs(): MutableList<Club>{
        val icon = context.resources.obtainTypedArray(R.array.club_image)
        val name = context.resources.getStringArray(R.array.club_name)
        val desc = context.resources.getStringArray(R.array.club_desc)
        val items = mutableListOf<Club>()

        for(i in name.indices){
            items.add(Club(icon.getResourceId(i,0),name[i],desc[i]))
        }
        icon.recycle()
        return items
    }

}