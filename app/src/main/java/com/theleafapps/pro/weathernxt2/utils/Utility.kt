package com.theleafapps.pro.weathernxt2.utils

import java.text.SimpleDateFormat
import java.util.*

class Utility {
    companion object {
        fun getTime(s: String): String? {
            return try {
                val sdf = SimpleDateFormat("h:mm a")
                val netDate = Date(s.toLong() * 1000)
                sdf.format(netDate)
            } catch (e: Exception) {
                e.toString()
            }
        }
    }
}