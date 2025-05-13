package com.jesushz.dogdirectory.core.presentation.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jesushz.dogdirectory.R
import com.jesushz.dogdirectory.core.presentation.designsystem.theme.DogDirectoryTheme

@Composable
fun DialogExit(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_exit_confirm),
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_exit_cancel),
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_exit_title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_exit_description),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
        )
    }
}

@Preview
@Composable
private fun DialogExitPreview() {
    DogDirectoryTheme {
        DialogExit(
            showDialog = true,
            onDismiss = { },
            onConfirm = { }
        )
    }
}