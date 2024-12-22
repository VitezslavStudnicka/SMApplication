package com.vitezslavstudnicka.smapplication.presentation.player.list

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.vitezslavstudnicka.smapplication.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PlayerListScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loadingStateShowsProgressIndicator() {
        composeRule.onNodeWithTag("loading_indicator").assertExists()
    }
}