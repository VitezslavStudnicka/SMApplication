package com.vitezslavstudnicka.smapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayerResponseDto(
    val data: PlayerDto
)

data class PlayerDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("height")
    val height: String,

    @SerializedName("weight")
    val weight: String,

    @SerializedName("jersey_number")
    val jerseyNumber: String,

    @SerializedName("college")
    val college: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("draft_year")
    val draftYear: Int,

    @SerializedName("draft_round")
    val draftRound: Int,

    @SerializedName("draft_number")
    val draftNumber: Int,

    @SerializedName("team")
    val team: TeamDto
)


