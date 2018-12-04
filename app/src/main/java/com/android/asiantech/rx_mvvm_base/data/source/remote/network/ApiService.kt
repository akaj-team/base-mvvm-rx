package com.android.asiantech.rx_mvvm_base.data.source.remote.network

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 *
 * @author at-haingo
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

    @GET("v1/comic/home/home.php")
    fun getComics(@Query("page") page: Int): Single<HomeResponse>

    @FormUrlEncoded
    @POST("v1/comic/favorite/star.php")
    fun favorite(@Field("id") id: Int): Single<FavoriteResponse>

    @FormUrlEncoded
    @POST("v1/comic/favorite/unstar.php")
    fun unFavorite(@Field("id") id: Int): Single<FavoriteResponse>
}
