package com.android.asiantech.rx_mvvm_base.data.source.remote.network

import com.android.asiantech.rx_mvvm_base.data.source.util.BaseRxCallAdapterWrapper
import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

/**
 *
 * @author at-hoavo.
 */
class RxCallAdapterWrapper<R>(type: Type, retrofit: Retrofit, wrapped: CallAdapter<R, *>?) : BaseRxCallAdapterWrapper<R>(type, retrofit, wrapped) {

    override fun convertRetrofitExceptionToCustomException(throwable: Throwable, retrofit: Retrofit): Throwable {

        if (throwable is HttpException) {
            val converter: Converter<ResponseBody, ApiException> = retrofit.responseBodyConverter(ApiException::class.java, arrayOfNulls<Annotation>(0))
            val response: Response<*>? = throwable.response()
            when (response?.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> response.errorBody()?.let {
                    val apiException = converter.convert(it)
                    apiException.statusCode = HttpsURLConnection.HTTP_UNAUTHORIZED
                    return apiException
                }

                HttpsURLConnection.HTTP_BAD_REQUEST -> response.errorBody()?.let {
                    return converter.convert(it).apply {
                        statusCode = HttpsURLConnection.HTTP_BAD_REQUEST
                    }
                }

                HttpsURLConnection.HTTP_INTERNAL_ERROR -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_FORBIDDEN -> response.errorBody()?.let {
                    val apiException = converter.convert(it)
                    apiException.statusCode = HttpURLConnection.HTTP_FORBIDDEN
                    return apiException
                }

                HttpsURLConnection.HTTP_NOT_FOUND -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_NOT_ACCEPTABLE -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_CONFLICT -> response.errorBody()?.let {
                    return converter.convert(it).apply {
                        statusCode = HttpsURLConnection.HTTP_CONFLICT
                    }
                }
            }
        }

        if (throwable is UnknownHostException) {
            // Set message error of this case in activity extension
            val apiException = ApiException("", mutableListOf())
            apiException.statusCode = ApiException.NETWORK_ERROR_CODE
            return apiException
        }

        if (throwable is SocketTimeoutException) {
            val apiException = ApiException("", mutableListOf())
            apiException.statusCode = HttpURLConnection.HTTP_CLIENT_TIMEOUT
            return apiException
        }

        return throwable
    }

    override fun createExceptionForSuccessResponse(response: Any?): Throwable? = super.createExceptionForSuccessResponse(response)
}
