@file:Suppress("unused")

package org.narussia.justfortoday.utils

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T : Any> Call<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resumeWith(
                    runCatching {
                        if (response.isSuccessful) {
                            response.body()
                                ?: throw NullPointerException("Response body is null: $response")
                        } else {
                            throw HttpException(response)
                        }
                    }
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resumeWithException(t)
            }
        })

        registerOnCompletion(continuation)
    }
}

suspend fun <T : Any?> Call<T>.awaitResponse(): Response<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resume(response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resumeWithException(t)
            }
        })

        registerOnCompletion(continuation)
    }
}

suspend fun <T : Any> Call<T>.awaitResult(): ResultRetrofit<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(
                    runCatching {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                ResultRetrofit.Exception(NullPointerException("Response body is null"))
                            } else {
                                ResultRetrofit.Ok(body, response.raw())
                            }
                        } else {
                            ResultRetrofit.Error(HttpException(response), response.raw())
                        }
                    }
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                continuation.resume(ResultRetrofit.Exception(t))
            }
        })

        registerOnCompletion(continuation)
    }
}

private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
            // Ignore cancel exception
        }
    }
}

/**
 * Sealed class of HTTP result
 */
@Suppress("unused")
sealed class ResultRetrofit<out T : Any> {
    /**
     * Successful result of request without errors
     */
    class Ok<out T : Any>(
        val value: T,
        override val response: okhttp3.Response
    ) : ResultRetrofit<T>(), ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }

    /**
     * HTTP error
     */
    class Error(
        override val exception: HttpException,
        override val response: okhttp3.Response
    ) : ResultRetrofit<Nothing>(), ErrorResult, ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    class Exception(
        override val exception: Throwable
    ) : ResultRetrofit<Nothing>(), ErrorResult {
        override fun toString() = "Result.Exception{$exception}"
    }
}

/**
 * Interface for [ResultRetrofit] classes with [okhttp3.Response]: [ResultRetrofit.Ok] and [ResultRetrofit.Error]
 */
interface ResponseResult {
    val response: okhttp3.Response
}

/**
 * Interface for [ResultRetrofit] classes that contains [Throwable]: [ResultRetrofit.Error] and [ResultRetrofit.Exception]
 */
interface ErrorResult {
    val exception: Throwable
}

/**
 * Returns [ResultRetrofit.Ok.value] or `null`
 */
fun <T : Any> ResultRetrofit<T>.getOrNull(): T? = (this as? ResultRetrofit.Ok)?.value

/**
 * Returns [ResultRetrofit.Ok.value] or [default]
 */
fun <T : Any> ResultRetrofit<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Returns [ResultRetrofit.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
fun <T : Any> ResultRetrofit<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is ResultRetrofit.Ok -> value
        is ResultRetrofit.Error -> throw throwable ?: exception
        is ResultRetrofit.Exception -> throw throwable ?: exception
    }
}
