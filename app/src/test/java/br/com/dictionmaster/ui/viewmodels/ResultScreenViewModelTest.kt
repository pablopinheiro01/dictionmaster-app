package br.com.dictionmaster.ui.viewmodels

import BOSS_SEARCH
import ERROR
import LIST_WORDS
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import br.com.dictionmaster.BaseTest
import br.com.dictionmaster.exceptions.AllAttemptsMadeBuyAppException
import br.com.dictionmaster.exceptions.DataNotFoundException
import br.com.dictionmaster.navigation.wordArgument
import br.com.dictionmaster.repositories.SearchRepository
import br.com.dictionmaster.ui.uistates.ResultScreenUiState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResultScreenViewModelTest : BaseTest() {

    @InjectMockKs
    private lateinit var viewModel: ResultScreenViewModel

    private var repository: SearchRepository = mockk()

    private var savedStateHandle: SavedStateHandle = mockk()

    @Before
    override fun beforEach() {

        coEvery { savedStateHandle.get<String>(wordArgument) } returns BOSS_SEARCH

        viewModel = spyk(
            ResultScreenViewModel(
                repository,
                savedStateHandle
            )
        )
    }

    @Test
    fun `GIVE search word WHEN user tap the word THEN execute proccess`() {
        runTest {
            coEvery { repository.search(BOSS_SEARCH) } returns LIST_WORDS
            viewModel.search()
            coVerify { viewModel.search() }
            viewModel.uiState.test {
                assert(awaitItem() is ResultScreenUiState.SuccessResultScreenUiState)
            }
        }
    }

    @Test
    fun `GIVE search word WHEN user tap the wrong word THEN show error state`() = runTest {
        coEvery { repository.search(BOSS_SEARCH) } throws DataNotFoundException(ERROR)
        viewModel.search()
        coVerify { viewModel.search() }
        viewModel.uiState.test {
            assert(awaitItem() is ResultScreenUiState.Error)
        }
    }

    @Test
    fun `GIVE search word WHEN user tap the last permited word THEN show buy state`() = runTest {
        coEvery { repository.search(BOSS_SEARCH) } throws AllAttemptsMadeBuyAppException(ERROR)
        viewModel.search()
        coVerify { viewModel.search() }
        viewModel.uiState.test {
            assert(awaitItem() is ResultScreenUiState.NavigateToBuy)
        }
    }

    @After
    override fun afterEach() {
        clearAllMocks()
    }

}