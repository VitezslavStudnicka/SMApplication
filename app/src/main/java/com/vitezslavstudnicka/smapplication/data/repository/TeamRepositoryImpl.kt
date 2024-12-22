package com.vitezslavstudnicka.smapplication.data.repository

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.data.mapper.toDomain
import com.vitezslavstudnicka.smapplication.data.remote.api.NbaApiService
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId
import com.vitezslavstudnicka.smapplication.domain.nba.repository.TeamRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val api: NbaApiService,
    private val apiKey: String
) : TeamRepository {
    override suspend fun getTeam(id: TeamId): Result<Team> = try {
        val response = api.getTeam(apiKey = apiKey, id = id.value)
        if (response.isSuccessful) {
            response.body()?.let { dto ->
                Result.Success(dto.data.toDomain())
            } ?: Result.Error(NetworkError.Unknown("Response body was null"))
        } else {
            Result.Error(NetworkError.ServerError(response.message()))
        }
    } catch (e: NetworkError) {
        Result.Error(e)
    } catch (e: Exception) {
        Result.Error(NetworkError.Unknown(e.message))
    }

//    override suspend fun getTeam(id: TeamId): Result<Team> = try {
//        when (val response = api.getTeam(apiKey = apiKey, id = id.value)) {
//            is Result.Success -> Result.Success(response.data.data.first().toDomain())
//            is Result.Error -> Result.Error(response.error)
//        }
//    } catch (e: NetworkError) {
//        Result.Error(e)
//    } catch (e: Exception) {
//        Result.Error(NetworkError.Unknown(e.message))
//    }
}