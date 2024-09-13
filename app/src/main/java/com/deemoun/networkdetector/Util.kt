package com.deemoun.networkdetector

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class Util {

    fun pingUrl(url: String): Int {
        return try {
            val urlObj = URL(url)
            val connection = urlObj.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000  // Increase timeout
            connection.readTimeout = 10000     // Increase timeout
            connection.connect()

            val responseCode = connection.responseCode
            Log.d("PingResponse", "Response code: $responseCode")

            // Return the response code, for example 200 means success
            responseCode
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PingError", "Error during ping: ${e.message}")
            -1 // Return -1 in case of failure
        }
    }
}
