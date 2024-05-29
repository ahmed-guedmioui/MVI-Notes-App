package com.ahmed_apps.notes_app_testing.add_note.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmed_apps.notes_app_testing.MainCoroutineRule
import com.ahmed_apps.notes_app_testing.add_note.domain.use_case.SearchImages
import com.ahmed_apps.notes_app_testing.add_note.domain.use_case.UpsertNote
import com.ahmed_apps.notes_app_testing.core.data.repository.FakeImagesRepository
import com.ahmed_apps.notes_app_testing.core.data.repository.FakeNoteRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * @author Ahmed Guedmioui
 */
class AddNoteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var addNoteViewModel: AddNoteViewModel

    private lateinit var fakeNotesRepositoryImpl: FakeNoteRepository
    private lateinit var fakeImagesRepository: FakeImagesRepository

    @Before
    fun setUp() {
        fakeNotesRepositoryImpl = FakeNoteRepository()
        fakeImagesRepository = FakeImagesRepository()
        val upsertNote = UpsertNote(fakeNotesRepositoryImpl)
        val searchImages = SearchImages(fakeImagesRepository)
        addNoteViewModel = AddNoteViewModel(upsertNote, searchImages)
    }

    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "",
            description = "description",
            imageUrl = "image"
        )

        Truth.assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title",
            description = "",
            imageUrl = "image"
        )

        Truth.assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert a valid, return true`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title",
            description = "description",
            imageUrl = "image"
        )

        Truth.assertThat(isInserted).isTrue()
    }

    @Test
    fun `search image with empty query, image list is empty`() = runTest {
        addNoteViewModel.searchImages("")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(
            addNoteViewModel.addNoteState.value.imageList.isEmpty()
        ).isTrue()
    }

    @Test
    fun `search image with a valid query but with error, image list is empty`() = runTest {
        fakeImagesRepository.setShouldReturnError(true)

        addNoteViewModel.searchImages("query")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(
            addNoteViewModel.addNoteState.value.imageList.isEmpty()
        ).isTrue()
    }

    @Test
    fun `search image with a valid query, image list is not empty`() = runTest {

        addNoteViewModel.searchImages("query")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(
            addNoteViewModel.addNoteState.value.imageList.isNotEmpty()
        ).isTrue()
    }
}

















