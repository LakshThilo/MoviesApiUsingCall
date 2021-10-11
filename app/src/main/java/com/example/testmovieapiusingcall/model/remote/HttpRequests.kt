package com.example.testmovieapiusingcall.model.remote

import com.example.testmovieapiusingcall.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object HttpRequests {

 val api: MovieApi by lazy {

     initRetrofit().create(MovieApi::class.java)
 }

    private fun initRetrofit() : Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val trustCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {

                }

                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {

                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustCerts, SecureRandom())
        // socket factory
        val sslSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory)
        builder.hostnameVerifier(HostnameVerifier{ hostName: String, session: SSLSession ->
            true
        })
        return builder.build()
    }
}

/*
* Classic Tab should load:
https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
Pop Tab should load:
https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50

Rock Tab should load:
https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
* */