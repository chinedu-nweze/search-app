package com.search.app.util

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun timeHelper(timeStamp: String): String {
        val date =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(timeStamp)
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(date)


    }
}