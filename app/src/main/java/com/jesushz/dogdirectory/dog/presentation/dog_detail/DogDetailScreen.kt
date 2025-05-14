package com.jesushz.dogdirectory.dog.presentation.dog_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.core.presentation.designsystem.theme.DogDirectoryTheme
import com.jesushz.dogdirectory.dog.presentation.dog_detail.components.BlurredImageBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogDetailScreenRoot(
    viewModel: DogDetailViewModel = koinViewModel(),
    onNavigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DogDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                DogDetailAction.OnBackClick -> onNavigateUp()
            }
        }
    )
}

@Composable
private fun DogDetailScreen(
    state: DogDetailState,
    onAction: (DogDetailAction) -> Unit
) {
    BlurredImageBackground(
        imageUrl = state.dog?.image.orEmpty(),
        onBackClick = {
            onAction(DogDetailAction.OnBackClick)
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = state.dog?.name.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            state.dog?.age?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Age: $it years old",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = state.dog?.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun DogDetailScreenPreview() {
    DogDirectoryTheme {
        DogDetailScreen(
            state = DogDetailState(
                dog = Dog(
                    name = "Rex",
                    description = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
                    image = "",
                    age = 5
                )
            ),
            onAction = {}
        )
    }
}