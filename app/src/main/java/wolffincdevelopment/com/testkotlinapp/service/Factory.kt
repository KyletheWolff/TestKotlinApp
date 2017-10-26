package wolffincdevelopment.com.testkotlinapp.service

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wolffincdevelopment.com.testkotlinapp.R
import wolffincdevelopment.com.testkotlinapp.service.api.WeatherApi


/**
 * Created by Kyle Wolff on 10/18/17.
 */
class Factory {

    companion object {

        fun getWeatherApi(c: Context): WeatherApi {

            val builder =  Retrofit.Builder()
                    .baseUrl(c.resources.getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient(c))
                    .build()

            return builder.create(WeatherApi::class.java)
        }

        private fun getOkHttpClient(c: Context): OkHttpClient {
            val client = OkHttpClient().newBuilder().addInterceptor({
                chain ->
                val request = chain.request()
                val originalHttpUrl = request.url()

                val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("APPID",  c.resources.getString(R.string.app_id))
                        .build()

                val requestBuilder = request.newBuilder().url(url)

                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            })

            val logInt = HttpLoggingInterceptor()
            logInt.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logInt)

            return client.build()
        }
    }
}