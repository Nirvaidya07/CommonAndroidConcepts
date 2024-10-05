/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :12:19 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.data.remote

import com.nirali.sample.recipe.components.Constants.Companion.BASEURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object
    {
        private val retrofit by lazy {
            val loggingInterceptor =HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client=OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()


        }

        val api by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}