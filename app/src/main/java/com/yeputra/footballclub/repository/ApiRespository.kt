package com.yeputra.footballclub.repository

import com.yeputra.footballclub.model.Event
import com.yeputra.footballclub.model.Events
import com.yeputra.footballclub.utils.BASE_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by yovi.putra
 *    on 08/Mar/2019 11:12
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface ApiRespository {

    @GET(BASE_URL + "eventspastleague.php?id={leagueId}")
    fun getLastMatch(@Path("leagueId") leagueId: String): Call<Events>

    @GET(BASE_URL + "eventsnextleague.php?id={leagueId}")
    fun getNextMatch(@Path("leagueId") leagueId: String): Call<Events>

    @GET(BASE_URL + "lookupevent.php?id={eventId}")
    fun getDetail(@Path("eventId") eventId: String): Call<Event>
}