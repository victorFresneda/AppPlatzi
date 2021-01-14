package com.appplatzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appplatzi.conf.model.Conference
import com.appplatzi.conf.network.Callback
import com.appplatzi.conf.network.FirestoreSevice
import java.lang.Exception

class ScheduleViewModel: ViewModel(){
    val firestoreService = FirestoreSevice()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh (){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object:Callback<List<Conference>>  {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()

            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })

    }

    private fun processFinished( ){

        isLoading.value = true
    }
}