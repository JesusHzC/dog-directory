package com.jesushz.dogdirectory.dog.presentation.dog_list

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesushz.dogdirectory.R
import com.jesushz.dogdirectory.core.presentation.designsystem.components.DogDirectoryScaffold
import com.jesushz.dogdirectory.core.presentation.designsystem.theme.DogDirectoryTheme
import com.jesushz.dogdirectory.core.presentation.ui.ObserveAsEvents
import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.core.presentation.designsystem.components.DialogExit
import com.jesushz.dogdirectory.dog.presentation.dog_list.components.DogListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogListScreenRoot(
    viewModel: DogListViewModel = koinViewModel(),
    onExit: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var showExitDialog by remember { mutableStateOf(false) }

    DialogExit(
        showDialog = showExitDialog,
        onDismiss = { showExitDialog = false },
        onConfirm = {
            onExit()
        }
    )
    ObserveAsEvents(
        flow = viewModel.event
    ) { event ->
        when (event) {
            is DogListEvent.OnError -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    DogListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                DogListAction.OnBackClick -> {
                    showExitDialog = true
                }
            }
        }
    )
}

@Composable
private fun DogListScreen(
    state: DogListState,
    onAction: (DogListAction) -> Unit
) {
    BackHandler {
        onAction(DogListAction.OnBackClick)
    }
    DogDirectoryScaffold(
        title = stringResource(R.string.dogs_we_love),
        onBackClick = {
            onAction(DogListAction.OnBackClick)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }
                state.dogs.isEmpty() -> {
                    Text(
                        text = stringResource(R.string.no_search_results),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
                else -> {
                    DogList(
                        dogs = state.dogs,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun DogList(
    modifier: Modifier = Modifier,
    dogs: List<Dog>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = dogs,
            key = { it.id }
        ) { dog ->
            DogListItem(
                dog = dog,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun DogListScreenPreview() {
    DogDirectoryTheme {
        DogListScreen(
            state = DogListState(
                isLoading = false
            ),
            onAction = {}
        )
    }
}
