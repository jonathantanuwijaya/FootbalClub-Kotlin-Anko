package com.yeputra.footballclub.repository.api

import com.yeputra.footballclub.model.Events
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.utils.BASE_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by yovi.putra
 *    on 08/Mar/2019 11:12
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface ApiRespository {

    @GET(BASE_URL + "eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): Call<Events>

    @GET(BASE_URL + "eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: String): Call<Events>

    @GET(BASE_URL + "lookupevent.php")
    fun getDetail(@Query("id") eventId: String): Call<Events>

    @GET(BASE_URL + "searchteams.php?")
    fun getTeam(@Query("t") teamId: String): Call<Team>
}