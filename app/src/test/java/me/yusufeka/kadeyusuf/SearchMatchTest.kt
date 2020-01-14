package me.yusufeka.kadeyusuf

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.responses.MatchResponse
import me.yusufeka.kadeyusuf.viewmodels.SearchMatchViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SearchMatchTest {

    private lateinit var searchMatchViewModel: SearchMatchViewModel

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
    fun getSearchMatch() {
        val leagueId = 4328

        val response = MatchResponse(
            null, listOf(
                Event(
                    null, "", "", "", "", "", "$leagueId",
                    "", "", "", "", "", "",
                    "", "", "", "",
                    "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "", "",
                    "", "", "", "",
                    "", "", "", "", "", "", "",
                    "", "Soccer", "", "", "", "", "",
                    "", "", ""
                )
            )
        )

        assertTrue(searchMatchViewModel.isLoading().hasObservers())
        assertTrue(searchMatchViewModel.getMatch().hasObservers())
        runBlocking {
            `when`(
                matchRepository!!.searchMatch(
                    ArgumentMatchers.anyString()
                )
            )
                .thenReturn(response)

            searchMatchViewModel.searchMatch(ArgumentMatchers.anyString(), leagueId)

            verify(observerLoading,times(1))?.onChanged(true)
            verify(observerMatch)?.onChanged(response.event)
            verify(observerLoading, times(2))?.onChanged(false)
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        searchMatchViewModel = SearchMatchViewModel(
            matchRepository!!
        )
        searchMatchViewModel.isLoading().observeForever(observerLoading!!)
        searchMatchViewModel.getMatch().observeForever(observerMatch!!)


    }
}