package com.youssef.network.data.errors

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.youssef.network.data.entities.errors.ErrorMessage
import com.youssef.network.data.entities.errors.ErrorTypes
import com.youssef.network.data.entities.errors.ServerError
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import retrofit2.HttpException
import timber.log.Timber

fun Throwable.getType(): ErrorTypes =
    when (this) {
        is ConnectException, is UnknownHostException -> ErrorTypes.NetworkError
        is HttpException -> ErrorTypes.HttpError(this)
        is TimeoutException -> ErrorTypes.TimeOut
        else -> ErrorTypes.UnKnown(this)
    }

fun ErrorTypes.getMessage(): ErrorMessage =
    when (this) {
        is ErrorTypes.NetworkError -> ErrorMessage(type = ErrorTypes.NetworkError)
        is ErrorTypes.TimeOut -> ErrorMessage(type = ErrorTypes.TimeOut)
        is ErrorTypes.HttpError -> getHttpError(throwable as HttpException)
        is ErrorTypes.UnKnown -> ErrorMessage(throwable.message, ErrorTypes.UnKnown(throwable))
    }

private fun getHttpError(throwable: HttpException): ErrorMessage {
    var message: String? = throwable.message()
    val gson = Gson()
    val type = object : TypeToken<ServerError>() {}.type
    throwable.response()?.errorBody()?.let {
        message = try {
            gson.fromJson<ServerError>(it.string(), type)?.message
        } catch (e: JsonParseException) {
            Timber.e(e)
            null
        } catch (e: JsonSyntaxException) {
            Timber.e(e)
            null
        }
    }
    return ErrorMessage(message, ErrorTypes.HttpError(throwable))
}
