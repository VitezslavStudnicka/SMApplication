package com.vitezslavstudnicka.smapplication.data.remote.api

import com.vitezslavstudnicka.smapplication.data.remote.dto.PlayerResponseDto
import com.vitezslavstudnicka.smapplication.data.remote.dto.PlayersResponseDto
import com.vitezslavstudnicka.smapplication.data.remote.dto.TeamsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NbaApiService {
    @GET("v1/players")
    suspend fun getPlayers(
        @Header("Authorization") apiKey: String,
        @Query("cursor") cursor: String? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("search") search: String? = null,
        @Query("first_name") firstName: String? = null,
        @Query("last_name") lastName: String? = null,
        @Query("team_ids[]") teamIds: List<Long>? = null,
        @Query("player_ids[]") playerIds: List<Long>? = null
    ): Response<PlayersResponseDto>

    @GET("v1/players/{id}")
    suspend fun getPlayer(
        @Header("Authorization") apiKey: String,
        @Path("id") id: Long
    ): Response<PlayerResponseDto>

    @GET("v1/teams/{id}")
    suspend fun getTeam(
        @Header("Authorization") apiKey: String,
        @Path("id") id: Long
    ): Response<TeamsResponseDto>
}