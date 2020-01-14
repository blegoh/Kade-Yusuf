package me.yusufeka.kadeyusuf

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import me.yusufeka.kadeyusuf.models.LeagueX
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.responses.LeagueResponse
import me.yusufeka.kadeyusuf.viewmodels.LeagueViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class DetailLeagueTest {

    private lateinit var leagueViewModel: LeagueViewModel

    @Mock
    var leagueRepository: LeagueRepository? = null

    @Mock
    var matchRepository: MatchRepository? = null

    @Mock
    var observerLoading: Observer<Boolean>? = null

    @Mock
    var observerLeague: Observer<LeagueX>? = null

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun getDetailLeague() {

        val leagueId = 4328

        val lookUpLeagueResponse = LeagueResponse(listOf(
            LeagueX("","","","","","",
                "","","","","","",
                "","","","","","",
                "","","","","","",
                "","","","","","","","",
                "","","","","","","","",
                "","","")
        ))

        assertTrue(leagueViewModel.isLoading().hasObservers())
        assertTrue(leagueViewModel.getLeague().hasObservers())

        runBlocking {
            `when`(leagueRepository!!.getLeague(ArgumentMatchers.anyInt()))
                .thenReturn(lookUpLeagueResponse)

            leagueViewModel.updateLeague(leagueId)

            verify(observerLoading,times(1))?.onChanged(true)
            verify(observerLeague)?.onChanged(lookUpLeagueResponse.leagues!![0])
            verify(observerLoading, times(2))?.onChanged(false)
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        leagueViewModel = LeagueViewModel(
            leagueRepository!!
        )
        leagueViewModel.isLoading().observeForever(observerLoading!!)
        leagueViewModel.getLeague().observeForever(observerLeague!!)
    }

}