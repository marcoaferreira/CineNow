package com.devspacecinenow.detail

import com.devspacecinenow.detail.data.DetailService
import com.devspacecinenow.detail.presentation.MovieDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MovieDetailViewModelTest {

    private val detailService: DetailService = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    private val underTest by lazy {
        MovieDetailViewModel(detailService, testDispatcher)
    }


}