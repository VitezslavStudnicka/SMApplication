package com.vitezslavstudnicka.smapplication.data.mapper

import com.vitezslavstudnicka.smapplication.data.remote.dto.TeamDto
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId

fun TeamDto.toDomain(): Team = Team(
    id = TeamId(id),
    conference = conference,
    division = division,
    city = city,
    name = name,
    fullName = fullName,
    abbreviation = abbreviation
)
