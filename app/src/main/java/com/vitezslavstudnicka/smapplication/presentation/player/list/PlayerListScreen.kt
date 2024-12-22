package com.vitezslavstudnicka.smapplication.presentation.player.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vitezslavstudnicka.smapplication.presentation.player.list.components.PlayerListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(
    onPlayerClick: (Long) -> Unit,
) {
    val viewModel: PlayerListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(listState.firstVisibleItemIndex, listState.layoutInfo.totalItemsCount) {
        if (!state.isLoading && listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == state.players.size - 3) {
            viewModel.loadMore()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NBA Players") }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.error != null && state.players.isEmpty() -> {
                    Text(
                        text = "Error: ${state.error}",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    LazyColumn(
                        state = listState
                    ) {
                        items(
                            items = state.players,
                            key = { it.id.value }
                        ) { player ->
                            PlayerListItem(
                                player = player,
                                onClick = { onPlayerClick(player.id.value) }
                            )
                        }

                        item {
                            if (state.isLoading) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.testTag("loading_indicator"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}