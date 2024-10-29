package com.devspacecinenow.list

import app.cash.turbine.test
import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.devspacecinenow.list.presentation.ui.MovieUiData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieListViewModelTest {

    private val repository: MovieListRepository = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    private val underTest by lazy {
        MovieListViewModel(repository, testDispatcher)
    }

    @Test
    fun `Given fresh viewModel When collecting nowPlaying Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            whenever(repository.getNowPlaying()).thenReturn(Result.success(movies))

            // When
            underTest.uiNowPlaying.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "name1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }


            // Then assert expected value

        }
    }

    @Test
    fun `Given fresh viewModel When collecting nowPlaying Then assert loading state`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            whenever(repository.getNowPlaying()).thenReturn(Result.success(movies))

            // When
            val result = underTest.uiNowPlaying.value


            // Then assert expected value
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting topRated Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))

            // When
            underTest.uiTopRated.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "name1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }


            // Then assert expected value

        }
    }

    @Test
    fun `Given fresh viewModel When collecting topRated Then assert loading state`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))

            // When
            val result = underTest.uiTopRated.value


            // Then assert expected value
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected, result)
        }
    }



    @Test
    fun `Given fresh viewModel When collecting upcoming Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Upcoming.name
                )
            )
            whenever(repository.getUpcoming()).thenReturn(Result.success(movies))

            // When
            underTest.uiUpcoming.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "name1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }


            // Then assert expected value

        }
    }

    @Test
    fun `Given fresh viewModel When collecting upcoming Then assert loading state`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Upcoming.name
                )
            )
            whenever(repository.getUpcoming()).thenReturn(Result.success(movies))

            // When
            val result = underTest.uiUpcoming.value


            // Then assert expected value
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Given fresh viewModel When collecting popular Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            whenever(repository.getPopular()).thenReturn(Result.success(movies))

            // When
            underTest.uiPopular.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "name1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }


            // Then assert expected value

        }
    }

    @Test
    fun `Given fresh viewModel When collecting popular Then assert loading state`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "name1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            whenever(repository.getPopular()).thenReturn(Result.success(movies))

            // When
            val result = underTest.uiPopular.value


            // Then assert expected value
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected, result)
        }
    }
}