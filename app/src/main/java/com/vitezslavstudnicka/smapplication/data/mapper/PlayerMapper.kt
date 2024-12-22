package com.vitezslavstudnicka.smapplication.data.mapper

import com.vitezslavstudnicka.smapplication.data.remote.dto.PlayerDto
import com.vitezslavstudnicka.smapplication.data.remote.dto.PlayerResponseDto
import com.vitezslavstudnicka.smapplication.data.remote.dto.PlayersResponseDto
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId

fun PlayersResponseDto.toDomain(): List<Player> {
    return data.map { playerDto -> playerDto.toDomain() }
}

fun PlayerResponseDto.toDomain(): Player {
    return data.toDomain()
}

fun PlayerDto.toDomain(): Player = Player(
    id = PlayerId(id),
    firstName = firstName,
    lastName = lastName,
    position = position,
    height = height,
    weight = weight,
    jerseyNumber = jerseyNumber,
    college = college,
    country = country,
    draftYear = draftYear,
    draftRound = draftRound,
    draftNumber = draftNumber,
    team = team.toDomain()
)