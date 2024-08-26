package com.john.islamiv2.Utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocalManager {
    // Updates the app's locale based on the selected language
    fun setLocale(context: Context, localCode: String) {
        val local = Locale(localCode)
        Locale.setDefault(local)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(local)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }
}