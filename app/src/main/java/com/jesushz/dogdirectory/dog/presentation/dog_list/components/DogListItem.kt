package com.jesushz.dogdirectory.dog.presentation.dog_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.jesushz.dogdirectory.R
import com.jesushz.dogdirectory.dog.data.models.Dog
import timber.log.Timber

@Composable
fun DogListItem(
    modifier: Modifier = Modifier,
    dog: Dog
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            var imageLoadResult by remember {
                mutableStateOf<Result<Painter>?>(null)
            }
            val painter = rememberAsyncImagePainter(
                model = dog.image,
                onSuccess = {
                    imageLoadResult = if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(Exception("Invalid image size"))
                    }
                },
                onError = {
                    Timber.e(it.result.throwable)
                    imageLoadResult = Result.failure(it.result.throwable)
                }
            )
            when (val result = imageLoadResult) {
                null -> Unit
                else -> {
                    Image(
                        painter = if (result.isSuccess) painter else painterResource(R.drawable.ic_empty_data),
                        contentDescription = null,
                        contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .aspectRatio(
                                ratio =0.65f,
                                matchHeightConstraintsFirst = true
                            )
                    )
                }
            }
        }
        Surface(
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                bottomEnd = 16.dp
            ),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dog.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Almost ${dog.age} years",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}