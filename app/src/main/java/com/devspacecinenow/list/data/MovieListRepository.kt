package com.devspacecinenow.list.data

import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.local.LocalDataSource
import com.devspacecinenow.list.data.remote.RemoteDataSource
import javax.inject.Inject


class MovieListRepository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    suspend fun getNowPlaying(): Result<List<Movie>?> {
        return try {
            val result = remote.getNowPlaying()
            if(result.isSuccess) {
                val moviesRemote = result.getOrNull()?: emptyList()
                if(moviesRemote.isNotEmpty()){
                    local.updateLocalItems(moviesRemote)
                }
                // source of truth
                Result.success(local.getNowPlayingMovies())

            } else {
                val localData = local.getNowPlayingMovies()
                if(localData.isEmpty()){
                    return result
                } else {
                    Result.success(localData)
                }
            }



        } catch(ex: Exception){
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getTopRated(): Result<List<Movie>?> {
        return try {
            val result = remote.getTopRated()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull()?: emptyList()
                if(moviesRemote.isNotEmpty()){
                    local.updateLocalItems(moviesRemote)
                }

                // source of truth
                Result.success(local.getTopRatedMovies())

            } else {
                val localData = local.getTopRatedMovies()
                if(localData.isEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }



        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getPopular(): Result<List<Movie>?> {
        return try {
            val result = remote.getPopular()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull()?: emptyList()
                if(moviesRemote.isNotEmpty()){
                    local.updateLocalItems(moviesRemote)
                }
                // source of truth
                Result.success(local.getPopularMovies())

            } else {
                val localData = local.getPopularMovies()
                if(localData.isEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }


        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

    suspend fun getUpcoming(): Result<List<Movie>?> {
        return try {
            val result = remote.getUpcoming()
            if (result.isSuccess) {
                val moviesRemote = result.getOrNull()?: emptyList()
                if(moviesRemote.isNotEmpty()){
                    local.updateLocalItems(moviesRemote)
                }
                // source of truth
                Result.success(local.getUpcomingMovies())

            } else {
                val localData = local.getUpcomingMovies()
                if(localData.isEmpty()) {
                    return result
                } else {
                    Result.success(localData)
                }
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}