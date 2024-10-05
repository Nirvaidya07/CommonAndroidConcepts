package com.nirali.interview_api_call.domain.utils

import android.util.Log
import kotlinx.serialization.Serializable
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, code: Int? = null, data: T? = null) :
        Resource<T>(data, message, code)

    class Loading<T> : Resource<T>()
}


@Serializable
data class ErrorResponse(
    val success: Boolean,
    val message: String
)


suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.Success(body)
            } else {
                Resource.Error("Response body is null", response.code())
            }
        } else {
            Log.d("TAG", "safeApiCall: ${response}")
            Log.d("TAG", "safeApiCall: ${response.code()}")
            when (response.code()) {
                401 -> {
                    //onLogout()
                    Resource.Error("Unauthorized. Please check your credentials.", 401)
                }

                502 -> Resource.Error("Bad Gateway. Server is unavailable at the moment.", 502)
                else -> {
                    val errorResponse = response.errorBody()?.string()?.let {
                        kotlinx.serialization.json.Json.decodeFromString<ErrorResponse>(it)
                    }
                    Resource.Error(
                        errorResponse?.message ?: "An unknown error occurred",
                        response.code()
                    )
                }
            }
        }
    } catch (e: IOException) {
        Log.d("TAG", "safeApiCall: ${e}")
        when (e) {
            is SocketTimeoutException -> Resource.Error("Request timed out. Please try again later.")
            is UnknownHostException -> Resource.Error("Unable to connect to the server. Please try again later.")
            else -> Resource.Error("No internet connection. Please check your network and try again.${e.message}")
        }
    } catch (e: HttpException) {
        Resource.Error("An HTTP error occurred: ${e.message()}")
    } catch (e: Exception) {
        Resource.Error(e.message ?: "An unknown error occurred")
    }
}











