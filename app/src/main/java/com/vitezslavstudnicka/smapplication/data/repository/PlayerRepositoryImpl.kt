package com.vitezslavstudnicka.smapplication.data.repository

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.data.mapper.toDomain
import com.vitezslavstudnicka.smapplication.data.remote.api.NbaApiService
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId
import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    private val api: NbaApiService,
    private val apiKey: String
) : PlayerRepository {

    override suspend fun getPlayers(
        cursor: String?,
        perPage: Int
    ): Result<Pair<List<Player>, Int?>> = try {
        val response = api.getPlayers(apiKey = apiKey, cursor = cursor, perPage = perPage)
        if (response.isSuccessful) {
            response.body()?.let { dto ->
                Result.Success(Pair(dto.data.map { it.toDomain() }, dto.meta.nextCursor))
            } ?: Result.Error(NetworkError.Unknown("Response body was null"))
        } else {
            Result.Error(NetworkError.ServerError(response.message()))
        }
    } catch (e: NetworkError) {
        Result.Error(e)
    } catch (e: Exception) {
        Result.Error(NetworkError.Unknown(e.message))
    }

//    override suspend fun getPlayers(
//        cursor: String?,
//        perPage: Int
//    ): Result<List<Player>> = try {
//        val response = api.getPlayers(apiKey = apiKey, cursor = cursor, perPage = perPage)
//        if (response.isSuccessful) {
//            response.body()?.let { dto ->
//                Result.Success(dto.toDomain())
//            } ?: Result.Error(NetworkError.Unknown("Response body was null"))
//        } else {
//            Result.Error(NetworkError.ServerError(response.message()))
//        }
//    } catch (e: NetworkError) {
//        Result.Error(e)
//    } catch (e: Exception) {
//        Result.Error(NetworkError.Unknown(e.message))
//    }

    override suspend fun getPlayer(id: PlayerId): Result<Player> = try {
        val response = api.getPlayer(apiKey = apiKey, id = id.value)
        if (response.isSuccessful) {
            response.body()?.let { dto ->
                Result.Success(dto.toDomain())
            } ?: Result.Error(NetworkError.Unknown("Response body was null"))
        } else {
            Result.Error(NetworkError.ServerError(response.message()))
        }
    } catch (e: NetworkError) {
        Result.Error(e)
    } catch (e: Exception) {
        Result.Error(NetworkError.Unknown(e.message))
    }
}