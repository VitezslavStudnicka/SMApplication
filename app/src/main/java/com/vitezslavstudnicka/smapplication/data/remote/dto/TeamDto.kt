package com.vitezslavstudnicka.smapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamsResponseDto(
    val data: TeamDto
)

data class TeamDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("conference")
    val conference: String,

    @SerializedName("division")
    val division: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("abbreviation")
    val abbreviation: String
)
