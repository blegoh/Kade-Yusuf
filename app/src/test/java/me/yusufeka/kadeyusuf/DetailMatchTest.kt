package me.yusufeka.kadeyusuf

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.responses.TeamResponse
import me.yusufeka.kadeyusuf.viewmodels.MatchDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchTest {

    private lateinit var matchDetailViewModel: MatchDetailViewModel

    @Mock
    var observerHome: Observer<Team>? = null

    @Mock
    var observerAway: Observer<Team>? = null

    @Mock
    var teamRepository: TeamRepository? = null

    @Mock
    var matchRepository: MatchRepository? = null

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun getDetailMatch() {

        val lookUpTeamResponse = TeamResponse(
            listOf(
                Team(
                    null, "", "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", ""
                )
            )
        )

        assertTrue(matchDetailViewModel.getHomeTeam().hasObservers())
        assertTrue(matchDetailViewModel.getAwayTeam().hasObservers())

        runBlocking {
            `when`(teamRepository!!.getTeam(ArgumentMatchers.anyInt()))
                .thenReturn(lookUpTeamResponse)

            matchDetailViewModel.updateHomeTeam(ArgumentMatchers.anyInt())
            matchDetailViewModel.updateAwayTeam(ArgumentMatchers.anyInt())

            verify(observerHome)?.onChanged(lookUpTeamResponse.teams!![0])
            verify(observerAway)?.onChanged(lookUpTeamResponse.teams!![0])
        }
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        matchDetailViewModel = MatchDetailViewModel(
            teamRepository!!, matchRepository!!
        )
        matchDetailViewModel.getHomeTeam().observeForever(observerHome!!)
        matchDetailViewModel.getAwayTeam().observeForever(observerAway!!)
    }
}