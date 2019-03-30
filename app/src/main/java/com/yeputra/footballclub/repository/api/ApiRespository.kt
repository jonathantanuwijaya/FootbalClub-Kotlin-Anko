package com.yeputra.footballclub.repository.api

import com.yeputra.footballclub.model.*
import com.yeputra.footballclub.utils.BASE_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by yovi.putra
 *    on 08/Mar/2019 11:12
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface ApiRespository {
    @GET(BASE_URL + "lookuptable.php")
    fun getStandings(@Query("l") idLeague: String): Observable<StandingsResponse>

    @GET(BASE_URL + "lookup_all_teams.php")
    fun getTeams(@Query("id") idLeague: String): Observable<TeamsResponse>

    @GET(BASE_URL + "searchteams.php")
    fun getTeam(@Query("t") teamId: String): Observable<TeamsResponse>

    @GET(BASE_URL + "lookup_all_players.php")
    fun getPlayers(@Query("id") teamId: String): Observable<PlayersResponse>

    @GET(BASE_URL + "lookupplayer.php")
    fun getPlayer(@Query("id") playerId: String): Observable<PlayerResponse>

    @GET(BASE_URL + "eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): Observable<EventsResponse>

    @GET(BASE_URL + "eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: String): Observable<EventsResponse>

    @GET(BASE_URL + "lookupevent.php")
    fun getMatch(@Query("id") eventId: String): Observable<EventsResponse>

    @GET(BASE_URL + "searchevents.php")
    fun searchMatch(@Query("e") eventName: String): Observable<SearchMatchResponse>


}