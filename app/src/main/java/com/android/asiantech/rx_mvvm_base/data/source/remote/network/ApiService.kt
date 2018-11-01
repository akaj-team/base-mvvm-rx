package com.android.asiantech.rx_mvvm_base.data.source.remote.network

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ListFavoritesResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 *
 * @author at-vinhhuynh.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("v1/user/login/login.php")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Single<LoginResponse>

    @FormUrlEncoded
    @POST("v1/user/signup/signup.php")
    fun register(@Field("email") email: String, @Field("password") password: String,
                 @Field("avatar") avatar: String): Single<SignUpResponse>

    @GET("v1/user/favorite/favorite.php")
    fun getListFavorites(@Query("page") page: Int): Single<ListFavoritesResponse>
}
