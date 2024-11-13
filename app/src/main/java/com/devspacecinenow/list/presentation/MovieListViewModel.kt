package com.devspacecinenow.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.CineNowApplication
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.di.DispatcherIO
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.devspacecinenow.list.presentation.ui.MovieUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieListRepository,
    @DispatcherIO private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiNowPlaying = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiNowPlaying: StateFlow<MovieListUiState> = _uiNowPlaying

    private val _uiTopRated = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiTopRated: StateFlow<MovieListUiState> = _uiTopRated

    private val _uiUpcoming = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiUpcoming: StateFlow<MovieListUiState> = _uiUpcoming

    private val _uiPopular = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiPopular: StateFlow<MovieListUiState> = _uiPopular

    init {
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
        fetchPopularMovies()
    }

    private fun fetchNowPlayingMovies() {
        _uiNowPlaying.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val result = repository.getNowPlaying()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieUiData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiNowPlaying.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiNowPlaying.value = MovieListUiState(
                        isError = true,
                        errorMessage = "No internet connection"
                    )
                } else {
                    _uiNowPlaying.value = MovieListUiState(isError = true)
                }
            }
        }
    }

    private fun fetchTopRatedMovies() {
        _uiTopRated.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val result = repository.getTopRated()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieUiData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiTopRated.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiTopRated.value = MovieListUiState(
                        isError = true,
                        errorMessage = "No internet connection"
                    )
                } else {
                    _uiTopRated.value = MovieListUiState(isError = true)
                }
            }
        }
    }

    private fun fetchUpcomingMovies() {
        _uiUpcoming.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val result = repository.getUpcoming()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieUiData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiUpcoming.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiUpcoming.value = MovieListUiState(
                        isError = true,
                        errorMessage = "No internet connection"
                    )
                } else {
                    _uiUpcoming.value = MovieListUiState(isError = true)
                }
            }
        }
    }


    private fun fetchPopularMovies() {
        _uiPopular.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val result = repository.getPopular()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieUiData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiPopular.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiPopular.value = MovieListUiState(
                        isError = true,
                        errorMessage = "No internet connection"
                    )
                } else {
                    _uiPopular.value = MovieListUiState(isError = true)
                }
            }
        }
    }
}