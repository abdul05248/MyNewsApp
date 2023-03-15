package com.mynewsapp.mentor.viewmodel_test

import app.cash.turbine.test
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesViewModel
import com.mynewsapp.mentor.utils.DispatcherProvider
import com.mynewsapp.mentor.utils.Resource
import com.mynewsapp.mentor.utils.TestDispatcherProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var topHeadlinesRepository: TopHeadlinesRepository

    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.openMocks(this)

    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {

        runTest {

            doReturn(flowOf(emptyList<TopHeadlines>())).`when`(topHeadlinesRepository)
                .getTopHeadlines()
            doReturn(true).`when`(networkHelper).isNetworkConnected()

            val viewModel =
                TopHeadlinesViewModel(topHeadlinesRepository, networkHelper, dispatcherProvider)

            viewModel.articleList.test {

                assertEquals(Resource.success(emptyList<List<TopHeadlines>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()

            }

            verify(topHeadlinesRepository).getTopHeadlines()
            verify(networkHelper).isNetworkConnected()

        }
    }

    @Test
    fun givenDBData_whenFetch_shouldReturnSuccess() {

        //Ask$ priority
        runTest {
            doReturn(flowOf(emptyList<TopHeadlines>())).`when`(topHeadlinesRepository)
                .getTopHeadlinesFromDb()
            doReturn(false).`when`(networkHelper).isNetworkConnected()

            val viewModel =
                TopHeadlinesViewModel(topHeadlinesRepository, networkHelper, dispatcherProvider)

            viewModel.articleList.test {

                assertEquals(Resource.success(emptyList<List<TopHeadlines>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlinesRepository).getTopHeadlinesFromDb()
            verify(networkHelper).isNetworkConnected()
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {
            //Ask$
//            Why the error. What is expected and what is actual and how it works explain.
            val errorMessage = "Error Message For You"
            doReturn(flow<List<TopHeadlines>> {
                throw IllegalStateException(errorMessage)
            }).`when`(topHeadlinesRepository).getTopHeadlines()

            doReturn(true).`when`(networkHelper).isNetworkConnected()

            val viewModel =
                TopHeadlinesViewModel(topHeadlinesRepository, networkHelper, dispatcherProvider)

            viewModel.articleList.test {
                assertEquals(
                    Resource.error<List<TopHeadlines>>(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlinesRepository).getTopHeadlines()
            verify(networkHelper).isNetworkConnected()

        }
    }

    @After
    fun tearDown() {

    }

}