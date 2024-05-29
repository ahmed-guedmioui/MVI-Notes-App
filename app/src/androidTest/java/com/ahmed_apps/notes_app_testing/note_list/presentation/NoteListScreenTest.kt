package com.ahmed_apps.notes_app_testing.note_list.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.ahmed_apps.notes_app_testing.core.di.AppModule
import com.ahmed_apps.notes_app_testing.core.presentation.MainActivity
import com.ahmed_apps.notes_app_testing.core.presentation.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * @author Ahmed Guedmioui
 */

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NoteListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun insertNote_noteIsDisplayedInList() {
        insertNote(1)
        assertNoteIsDisplayed(1)
    }

    @Test
    fun deleteNote_noteIsNoteDisplayedInList() {
        insertNote(1)
        assertNoteIsDisplayed(1)

        deleteNote(1)
        assertNoteIsNotDisplayed(1)
    }


    @Test
    fun noteListScreenEndToEndTest() {
        insertNote(1)
        assertNoteIsDisplayed(1)

        insertNote(2)
        assertNoteIsDisplayed(2)

        insertNote(3)
        assertNoteIsDisplayed(3)

        deleteNote(1)
        assertNoteIsNotDisplayed(1)
        assertNoteIsDisplayed(2)
        assertNoteIsDisplayed(3)

        insertNote(4)
        deleteNote(3)
        assertNoteIsNotDisplayed(3)
        assertNoteIsDisplayed(2)
        assertNoteIsDisplayed(4)
    }

    private fun insertNote(noteNumber: Int) {
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_FAB)
            .performClick()

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("title $noteNumber")

        composeRule.onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
            .performTextInput("description $noteNumber")

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





















