package com.yeputra.footballclub.presenter

import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.Events
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.repository.api.ApiRespository
import com.yeputra.footballclub.utils.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:54
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class LeaguePresenter(val v: IBaseView): BasePresenter(v) {

    private val api: ApiRespository by lazy {
        RestClient
            .get()
            .create(ApiRespository::class.java)
    }

    fun getLastMatch(leagueId: String){
        v.showProgressbar()
        api.getLastMatch(leagueId).enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

    fun getNextMatch(leagueId: String){
        v.showProgressbar()
        api.getNextMatch(leagueId).enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(t.message)
            }
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

    fun getDetail(eventId: String) {
        v.showProgressbar()
        api.getDetail(eventId).enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

    fun getTeam(teamId: String){
        v.showProgressbar()
        api.getTeam(teamId).enqueue(object : Callback<Team> {
            override fun onFailure(call: Call<Team>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

}