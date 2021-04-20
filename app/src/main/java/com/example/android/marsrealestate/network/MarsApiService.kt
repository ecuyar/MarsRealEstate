package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

/*
Right now the goal is to get the JSON response string from the web service,
and you only need one method to do that: getProperties().
To tell Retrofit what this method should do, use a @GET annotation and specify the path, or endpoint,
for that web service method. In this case the endpoint is called realestate. When the getProperties()
method is invoked, Retrofit appends the endpoint realestate to the base URL
(which you defined in the Retrofit builder), and creates a Call object.
That Call object is used to start the request.
 */
interface MarsApiService {
    @GET("realestate")
    fun getProperties():
            Call<String>
}

/*
Below the MarsApiService interface, define a public object called
MarsApi to initialize the Retrofit service.
This is a standard Kotlin code pattern to use when creating a service object.
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}