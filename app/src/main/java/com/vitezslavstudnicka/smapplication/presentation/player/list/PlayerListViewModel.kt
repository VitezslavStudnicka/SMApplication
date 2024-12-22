package com.vitezslavstudnicka.smapplication.presentation.player.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.player.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PlayerListState())
    val state = _state.asStateFlow()

    /**
     * Initializes the ViewModel by loading the initial list of players.
     */
    init {
        loadPlayers()
    }

    /**
     * Loads the list of players based on the provided [cursor].
     *
     * If [cursor] is `null`, the initial set of players is fetched.
     * Otherwise, additional players are appended to the existing list
     * for pagination.
     *
     * @param cursor Optional cursor for paginated results.
     */
    fun loadPlayers(cursor: String? = null) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = getPlayersUseCase(cursor = cursor)) {
                is Result.Success -> _state.update {
                    it.copy(
                        players = if (cursor == null) result.data.first else it.players + result.data.first,
                        isLoading = false,
                        nextCursor = result.data.second?.toString()  // Using Pair to pass both data and cursor
                    )
                }

                is Result.Error -> _state.update {
                    it.copy(error = result.error, isLoading = false)
                }
            }
        }
    }

    /**
     * Loads more players if there is a next cursor available and not already loading.
     */
    fun loadMore() {
        state.value.nextCursor?.let { cursor ->
            if (!state.value.isLoading) {
                loadPlayers(cursor)
            }
        }
    }
}