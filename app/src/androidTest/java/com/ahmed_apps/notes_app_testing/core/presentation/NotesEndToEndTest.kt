package com.ahmed_apps.notes_app_testing.core.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.ahmed_apps.notes_app_testing.core.di.AppModule
import com.ahmed_apps.notes_app_testing.core.presentation.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Ahmed Guedmioui
 */

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun notesEndToEndTest() {
        for (i in 1..4) {
            insertNote(i)
        }

        deleteNote(2)
        assertNoteIsDisplayed(1)
        assertNoteIsNotDisplayed(2)
        assertNoteIsDisplayed(3)
        assertNoteIsDisplayed(4)

        insertNote(2)
        insertNote(5)

        deleteNote(3)

        assertNoteIsDisplayed(1)
        assertNoteIsDisplayed(2)
        assertNoteIsNotDisplayed(3)
        assertNoteIsDisplayed(4)
        assertNoteIsDisplayed(5)


    }

    private fun insertNote(noteNumber: Int) {
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_FAB)
            .performClick()

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("title $noteNumber")

        composeRule.onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
            .performTextInput("description $noteNumber")

        composeRule.onNodeWithTag(
            TestTags.NOTE_IMAGE
        ).performClick()

        composeRule.onNodeWithTag(TestTags.SEARCH_IMAGE_DIALOG)
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTags.SEARCH_IMAGE_TEXT_FIELD)
            .performTextInput("query$noteNumber")

        runBlocking {
            delay(600)
        }

        composeRule.onNodeWithTag(
            TestTags.PICKED_IMAGE + "image$noteNumber"
        )
            .performClick()

        composeRule.onNodeWithTag(
            TestTags.NOTE_IMAGE
        ).assertIsDisplayed()


        composeRule.onNodeWithTag(TestTags.SAVE_BUTTON)
            .performClick()

    }

    private fun deleteNote(noteNumber: Int) {
        composeRule.onNodeWithContentDescription(
            TestTags.DELETE_NOTE + "title $noteNumber"
        ).performClick()
    }

    private fun assertNoteIsDisplayed(noteNumber: Int) {
        composeRule.onNodeWithText("title $noteNumber")
            .assertIsDisplayed()
    }


    private fun assertNoteIsNotDisplayed(noteNumber: Int) {
        composeRule.onNodeWithText("title $noteNumber")
            .assertIsNotDisplayed()
    }
}























