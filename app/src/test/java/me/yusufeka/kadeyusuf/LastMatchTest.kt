package me.yusufeka.kadeyusuf

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.responses.MatchResponse
import me.yusufeka.kadeyusuf.viewmodels.LeagueViewModel
import me.yusufeka.kadeyusuf.viewmodels.MatchViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LastMatchTest {

    private lateinit var matchViewModel: MatchViewModel

    @Mock
    var matchRepository: MatchRepository? = null

    @Mock
    var observerLoading: Observer<Boolean>? = null

    @Mock
    var observerMatch: Observer<List<Event>>? = null

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun getLastMatch() {
        val leagueId = 4328

        val response = MatchResponse(
            listOf(
                Event(
                    null, "", "", "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "",
                    "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "",
                    "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "",
                    "", "", ""
                )
            ), null
        )

        assertTrue(matchViewModel.isLoading().hasObservers())
        assertTrue(matchViewModel.getLastMatch().hasObservers())
        runBlocking {
            `when`(matchRepository!!.getLastMatch(ArgumentMatchers.anyInt()))
                .thenReturn(response)

            matchViewModel.updateLastMatch(leagueId)

            verify(observerLoading, times(1))?.onChanged(true)
            verify(observerMatch)?.onChanged(response.events)
            verify(observerLoading, times(2))?.onChanged(false)
        }
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        matchViewModel = MatchViewModel(
            matchRepository!!
        )
        matchViewModel.isLoading().observeForever(observerLoading!!)
        matchViewModel.getLastMatch().observeForever(observerMatch!!)
    }
}