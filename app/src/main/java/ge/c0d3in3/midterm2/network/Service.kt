package ge.c0d3in3.midterm2.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class Service<T>(private val clazz: Class<T>) {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    private var client: T? = null

    private fun create(): T {
        if (client != null)
            return client!!

        val retrofitBuilder =
            Retrofit.Builder().baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        client = retrofitBuilder.create(clazz)
        return client!!
    }

    private fun getOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    fun get() = client ?: create()
}