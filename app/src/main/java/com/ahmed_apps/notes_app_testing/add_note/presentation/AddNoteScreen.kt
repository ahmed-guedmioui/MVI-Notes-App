package com.ahmed_apps.notes_app_testing.add_note.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.ahmed_apps.notes_app_testing.R
import com.ahmed_apps.notes_app_testing.core.presentation.util.TestTags
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Ahmed Guedmioui
 */

@Composable
fun AddNoteScreen(
    onSave: () -> Unit,
    addNoteViewModel: AddNoteViewModel = hiltViewModel()
) {

    val addNoteState by addNoteViewModel.addNoteState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(true) {
        addNoteViewModel.noteSavedFlow.collectLatest { saved ->
            if (saved) {
                onSave()
            } else {
                Toast.makeText(
                    context,
                    R.string.error_saving_note,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable {
                    addNoteViewModel.onAction(
                        AddNoteActions.UpdateImagesDialogVisibility
                    )
                }
                .testTag(
                    TestTags.NOTE_IMAGE
                ),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(addNoteState.imageUrl)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = addNoteState.searchImagesQuery,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(TestTags.TITLE_TEXT_FIELD),
            value = addNoteState.title,
            onValueChange = {
                addNoteViewModel.onAction(
                    AddNoteActions.UpdateTitle(it)
                )
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(TestTags.DESCRIPTION_TEXT_FIELD),
            value = addNoteState.description,
            onValueChange = {
                addNoteViewModel.onAction(
                    AddNoteActions.UpdateDescription(it)
                )
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(TestTags.SAVE_BUTTON),
            onClick = {
                addNoteViewModel.onAction(
                    AddNoteActions.SaveNote
                )
            }
        ) {
            Text(
                text = stringResource(R.string.save),
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }

    if (addNoteState.isImagesDialogShowing) {
        Dialog(
            onDismissRequest = {
                addNoteViewModel.onAction(
                    AddNoteActions.UpdateImagesDialogVisibility
                )
            }
        ) {
            ImagesDialogContent(
                addNoteState = addNoteState,
                onSearchQueryChange = {
                    addNoteViewModel.onAction(
                        AddNoteActions.UpdateSearchImageQuery(it)
                    )
                },
                onImageClick = {
                    addNoteViewModel.onAction(
                        AddNoteActions.PickImage(it)
                    )
                }
            )
        }
    }
}

@Composable
fun ImagesDialogContent(
    addNoteState: AddNoteState,
    onSearchQueryChange: (String) -> Unit,
    onImageClick: (String) -> Unit,
) {

    Log.d("ImagesDialogContent", "ImagesDialogContent")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.background)
            .testTag(TestTags.SEARCH_IMAGE_DIALOG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(TestTags.SEARCH_IMAGE_TEXT_FIELD),
            value = addNoteState.searchImagesQuery,
            onValueChange = {
                onSearchQueryChange(it)
            },
            label = {
                Text(text = stringResource(R.string.search_image))
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (addNoteState.isLoadingImages) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {

                itemsIndexed(addNoteState.imageList) { index, url ->
                    Log.d("ImagesDialogContent", ": $url")
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable { onImageClick(url) }
                            .testTag(TestTags.PICKED_IMAGE + url),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(url)
                            .size(Size.ORIGINAL)
                            .build(),
                        contentDescription = stringResource(R.string.image),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }
}





















