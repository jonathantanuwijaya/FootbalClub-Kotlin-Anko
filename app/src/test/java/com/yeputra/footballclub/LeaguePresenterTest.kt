package com.yeputra.footballclub

import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.*
import com.yeputra.footballclub.presenter.LeaguePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LeaguePresenterTest {
    private lateinit var presenter : LeaguePresenter

    @Mock
    private lateinit var view: IBaseView

    @Mock
    private lateinit var standingsResponse: StandingsResponse

    @Mock
    private lateinit var events: EventsResponse

    @Mock
    private lateinit var teamsResponse: TeamsResponse

    @Mock
    private lateinit var playersResponse: PlayersResponse

    @Mock
    private lateinit var leaguesResponse: LeaguesResponse

    private var league: String = "4328"

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view)

        spy(view.showProgressbar())
        spy(view.onPresenterSuccess(standingsResponse))
        spy(view.onPresenterSuccess(events))
        spy(view.onPresenterSuccess(teamsResponse))
        spy(view.onPresenterSuccess(playersResponse))
        spy(view.onPresenterSuccess(leaguesResponse))
        spy(view.hideProgressbar())
    }

    @Test
    fun getLeagues() {
        presenter.getLeagues()
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(leaguesResponse)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getLeague() {
        presenter.getLeague(league)
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(leaguesResponse)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getStandingTest() {
        presenter.getStanding(league)
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(standingsResponse)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getTeamsTest() {
        presenter.getTeams(league)
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(teamsResponse)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getPlayersTest() {
        presenter.getPlayers("133599")
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(playersResponse)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getNextEventTest(){
        presenter.getNextMatch(league)
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(events)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getPrevEventTest(){
        presenter.getLastMatch(league)
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(events)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getDetailEventTest(){
        presenter.getDetail("576769")
        verify(view, times(2)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(events)
        verify(view, times(1)).hideProgressbar()
    }

    @Test
    fun getTeamTest(){
        presenter.getTeam("Barchelona")
        verify(view).onPresenterSuccess(events)
    }

    @Test
    fun getSearchEventTest(){
        presenter.searchEvent("Barchelona")
        verify(view, times(1)).showProgressbar()
        verify(view, times(1)).onPresenterSuccess(events)
        verify(view, times(1)).hideProgressbar()
    }
}
