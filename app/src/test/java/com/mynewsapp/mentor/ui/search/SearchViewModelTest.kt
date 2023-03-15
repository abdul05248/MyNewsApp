package com.mynewsapp.mentor.ui.search

import com.mynewsapp.mentor.data.repository.SearchRepository
import com.mynewsapp.mentor.utils.DispatcherProvider
import com.mynewsapp.mentor.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {

        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.openMocks(this)

    }


    @Test
    fun given_resp_error_when_empty_search(){

        runTest {

//            doReturn()
        }

    }

    @After
    fun tearDown() {
    }
}