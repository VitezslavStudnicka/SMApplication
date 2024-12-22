package com.vitezslavstudnicka.smapplication.presentation.team.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.team.GetTeamDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getTeamDetailsUseCase: GetTeamDetailsUseCase
) : ViewModel() {
    /**
     * The state of the team details screen, exposed as a [StateFlow].
     */
    private val _state = MutableStateFlow(TeamDetailState())
    val state = _state.asStateFlow()

    /**
     * Loads the details of a team based on the provided [id].
     *
     * Updates the screen state with loading status, the fetched team details on success,
     * or an error message on failure.
     *
     * @param id The ID of the team to fetch.
     */
    fun loadTeam(id: TeamId) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = getTeamDetailsUseCase(id)) {
                is Result.Success -> _state.update {
                    it.copy(team = result.data, isLoading = false)
                }

                is Result.Error -> _state.update {
                    it.copy(error = result.error, isLoading = false)
                }
            }
        }
    }
}