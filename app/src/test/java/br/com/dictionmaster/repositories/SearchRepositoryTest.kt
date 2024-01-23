package br.com.dictionmaster.repositories

import BOSS_SEARCH
import LIST_WORDS
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.dictionmaster.BaseTest
import br.com.dictionmaster.database.dao.WordsDao
import br.com.dictionmaster.network.services.DictionMasterService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest : BaseTest() {

    @InjectMockKs
    private lateinit var repository: SearchRepository

    private var service: DictionMasterService = mockk()

    @MockK
    private lateinit var dao: WordsDao

    @MockK
    private lateinit var dataStore: DataStore<Preferences>

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = spyk(
            SearchRepository(
                service = service,
                dao = dao,
                dataStore = dataStore
            )
        )

    }

    @Test
    fun `GIVEN call search WHEN client request flow and taped value THEN verify if word exists in database or call api`() =
        runTest {
            coEvery { repository.search(BOSS_SEARCH) } returns LIST_WORDS

            val data = repository.search(BOSS_SEARCH)
            coVerify {
                repository.search(BOSS_SEARCH)
            }
            TestCase.assertEquals(LIST_WORDS, data)
        }


    @After
    override fun afterEach() {
        super.afterEach()
        clearAllMocks()
    }

}