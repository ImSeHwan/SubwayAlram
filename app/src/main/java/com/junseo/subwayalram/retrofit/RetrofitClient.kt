package com.junseo.subwayalram.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitClient {
    private const val TDATA_BASE_URL = "https://t-data.seoul.go.kr"
    private const val SWOPENAPI_BASE_URL = "http://swopenapi.seoul.go.kr/api/"

    private val okHttpClient = unSafeOkHttpClient()

    val tdataInstance: SubwayApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TDATA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(SubwayApiService::class.java)
    }

    val swopenapiInstance: SubwayApiService by lazy {
        Retrofit.Builder()
            .baseUrl(SWOPENAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SubwayApiService::class.java)
    }

    // 별도의 SSL 인증서 없이 우회하기
    private fun unSafeOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            if (trustAllCerts.isNotEmpty() && trustAllCerts.first() is X509TrustManager) {
                okHttpClient.sslSocketFactory(sslContext.socketFactory, trustAllCerts.first() as X509TrustManager)
                okHttpClient.hostnameVerifier { _, _ -> true }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return okHttpClient.build()
    }
}