package com.example.seller.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller.models.ListingDataModel
import com.example.seller.network.RetroService
import com.example.seller.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var listingLiveData: MutableLiveData<ListingDataModel> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<ListingDataModel> {
        return listingLiveData

    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getApiData()
            listingLiveData.postValue(response)


        }
    }
}