package com.appplatzi.conf.viewmodel


import androidx.lifecycle.MutableLiveData
import com.appplatzi.conf.model.Speaker
import com.appplatzi.conf.network.Callback
import com.appplatzi.conf.network.FirestoreSevice
import java.lang.Exception

class SpeakersViewModel {
    val firestoreService = FirestoreSevice()
    var listSpeaker:MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh (){
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase(){
        firestoreService.getSpeakers(object:Callback<List<Speaker>>  {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                prosessFinished()

            }

            override fun onFailed(exception: Exception) {
                prosessFinished()
            }
        })

    }

    fun prosessFinished( ){

        isLoading.value = true
    }
}