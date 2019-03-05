package com.yeputra.footballclub.main.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.main.adapter.ListClubAdapter
import com.yeputra.footballclub.main.model.Club
import kotlinx.android.synthetic.main.list_match.view.*

class ListMatchFm : Fragment() {
    private lateinit var matchAdapter: ListClubAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.list_match,container,false)

        matchAdapter = ListClubAdapter(mutableListOf(
            Club(0,"Barca",""),
            Club(0,"Barca",""),
            Club(0,"Barca",""),
            Club(0,"Barca","")
        )){}
        v.rv_match.layoutManager = LinearLayoutManager(context)
        v.rv_match.adapter = matchAdapter
        return v
    }


}
