package br.com.dictionmaster

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    @Before
    open fun beforEach(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    open fun afterEach(){
        Dispatchers.resetMain()
    }

}