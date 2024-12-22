package com.vitezslavstudnicka.smapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayersResponseDto(
    val data: List<PlayerDto>,
    val meta: MetaDto
)

data class MetaDto(
    @SerializedName("next_cursor")
    val nextCursor: Int?,

    @SerializedName("per_page")
    val perPage: Int
)