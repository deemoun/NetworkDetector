package com.deemoun.networkdetector

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

data class PingResult(
    val url: URL,
    val responseCode: Int
)

class Util {

    fun pingUrl(url: String): PingResult? {
        return try {
            val urlObj = URL(url)
            val connection = urlObj.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000  // Increase timeout
            connection.readTimeout = 10000     // Increase timeout
            connection.connect()

            val responseCode = connection.responseCode
            Log.d("PingResponse", "Response code: $responseCode")

            // Return a PingResult containing the URL object and response code
            PingResult(url = urlObj, responseCode = responseCode)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PingError", "Error during ping: ${e.message}")
            null // Return null in case of failure
        }
    }
}
