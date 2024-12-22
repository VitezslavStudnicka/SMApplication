package com.vitezslavstudnicka.smapplication.domain.nba.model

@JvmInline
value class PlayerId(val value: Long)

data class Player(
    val id: PlayerId,
    val firstName: String,
    val lastName: String,
    val position: String,
    val height: String,
    val weight: String,
    val jerseyNumber: String,
    val college: String,
    val country: String,
    val draftYear: Int,
    val draftRound: Int,
    val draftNumber: Int,
    val team: Team
)