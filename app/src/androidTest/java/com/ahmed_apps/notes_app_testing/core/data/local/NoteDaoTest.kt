package com.ahmed_apps.notes_app_testing.core.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ahmed_apps.notes_app_testing.core.di.AppModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
@HiltAndroidTest
@SmallTest
@UninstallModules(AppModule::class)
class NoteDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var noteDb: NoteDb
    private lateinit var dao: NoteDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = noteDb.noteDao
    }

    @After
    fun tearDown() {
        noteDb.close()
    }

    @Test
    fun getAllNotesFromEmptyDb_noteListIsEmpty() = runTest {
        assertThat(
            dao.getAllNoteEntities().isEmpty()
        )
    }


    @Test
    fun getAllNotesFromDb_noteListIsNotEmpty() = runTest {
        for (i in 1..4) {
            val noteEntity = NoteEntity(
                id = i,
                title = "title $i",
                description = "content $i",
                imageUrl = "image $i",
                dateAdded = System.currentTimeMillis(),
            )

            dao.upsertNoteEntity(noteEntity)
        }

        assertThat(
            dao.getAllNoteEntities().isNotEmpty()
        )
    }


    @Test
    fun upsertNote_noteIsUpserted() = runTest {
        val noteEntity = NoteEntity(
            id = 1,
            title = "title",
            description = "content",
            imageUrl = "image",
            dateAdded = System.currentTimeMillis(),
        )

        dao.upsertNoteEntity(noteEntity)

        assertThat(
            dao.getAllNoteEntities().contains(noteEntity)
        )
    }

    @Test
    fun deleteNote_noteIsDeleted() = runTest {
        val noteEntity = NoteEntity(
            id = 1,
            title = "title",
            description = "content",
            imageUrl = "image",
            dateAdded = System.currentTimeMillis(),
        )

        dao.upsertNoteEntity(noteEntity)

        assertThat(
            dao.getAllNoteEntities().contains(noteEntity)
        ).isTrue()

        dao.deleteNoteEntity(noteEntity)

        assertThat(
            !dao.getAllNoteEntities().contains(noteEntity)
        ).isTrue()
    }

}























