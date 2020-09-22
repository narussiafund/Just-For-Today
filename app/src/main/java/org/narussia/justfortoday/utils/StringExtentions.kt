package org.narussia.justfortoday.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.fromHtml(): Spanned? =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        else -> @Suppress("DEPRECATION") Html.fromHtml(this)
    }
