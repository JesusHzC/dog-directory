package com.jesushz.dogdirectory.core.presentation.ui

import com.jesushz.dogdirectory.R
import com.jesushz.dogdirectory.core.domain.DataError

fun DataError.asUiText(): UiText {
    return when(this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )
        DataError.Remote.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_request_timeout
        )
        DataError.Remote.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests
        )
        DataError.Remote.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )
        DataError.Remote.SERVER -> UiText.StringResource(
            R.string.error_server_error
        )
        DataError.Remote.SERIALIZATION -> UiText.StringResource(
            R.string.error_serialization
        )
        else -> UiText.StringResource(R.string.error_unknown)
    }
}
