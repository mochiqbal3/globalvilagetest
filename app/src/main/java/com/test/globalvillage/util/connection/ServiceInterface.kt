package com.gammasolution.segarmart.util.connection

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import com.test.globalvillage.BuildConfig
import com.test.globalvillage.model.ResponseProduct
import com.test.globalvillage.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

interface ServiceInterface {

    @GET("product")
    fun getProduct(@HeaderMap headerMap: Map<String, String>, @Query("limit") limit : Int, @Query("start") start : Int): Observable<ResponseProduct>

    @GET("product")
    fun getProductDetail(@HeaderMap headerMap: Map<String, String>,@Path("id") id : String): Observable<ResponseProduct>

    companion object {

        private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        var baseUrl : String = "http://ws2.globalvillage.co.id/"
        fun create(): ServiceInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ServiceInterface::class.java)
        }

        fun createAuthenticationHeader(): Map<String,  String>  {
            var headerMap: HashMap<String,  String> =HashMap<String,String>()
            headerMap.put("userkey",Constant.userkey)
            headerMap.put("passkey",Constant.passkey)
            headerMap.put("Accept", "application/json")
            headerMap.put("Content-Type", "application/json")

            return headerMap

        }

        fun createHeader(): Map<String,  String>  {
            var headerMap: Map<String,  String> = mutableMapOf<String, String>()

            headerMap.plus(Pair("Accept", "application/json"))
            headerMap.plus(Pair("Content-Type", "application/json"))

            return headerMap

        }

    }

}
