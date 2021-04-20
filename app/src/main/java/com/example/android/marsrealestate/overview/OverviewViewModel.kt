package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue(
                object : Callback, retrofit2.Callback<List<MarsProperty>> {
                    override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                        _response.value =
                                "Success: ${response.body()?.size} Mars properties retrieved"
                    }

                    override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                        _response.value = "Failure: " + t.message
                    }
                }
        )
    }
}

