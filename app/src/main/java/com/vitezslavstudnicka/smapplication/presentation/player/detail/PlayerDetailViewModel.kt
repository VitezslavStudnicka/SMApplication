package com.vitezslavstudnicka.smapplication.presentation.player.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.player.GetPlayerDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerDetailsUseCase: GetPlayerDetailsUseCase
) : ViewModel() {
    /**
     * The state of the player details screen, exposed as a [StateFlow].
     */
    private val _state = MutableStateFlow(PlayerDetailState())
    val state = _state.asStateFlow()


    /**
     * Loads the details of a player based on the provided [id].
     *
     * Updates the screen state with loading status, the fetched player details on success,
     * or an error message on failure.
     *
     * @param id The ID of the player to fetch.
     */
    fun loadPlayer(id: PlayerId) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = getPlayerDetailsUseCase(id)) {
                is Result.Success -> _state.update {
                    it.copy(player = result.data, isLoading = false)
                }

                is Result.Error -> _state.update {
                    it.copy(error = result.error, isLoading = false)
                }
            }
        }
    }
}