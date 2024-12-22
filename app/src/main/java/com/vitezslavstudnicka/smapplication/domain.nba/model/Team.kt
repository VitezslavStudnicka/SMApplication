package com.vitezslavstudnicka.smapplication.domain.nba.model

@JvmInline
value class TeamId(val value: Long)

data class Team(
    val id: TeamId,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val fullName: String,
    val abbreviation: String
)