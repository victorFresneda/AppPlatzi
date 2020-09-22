package viewmodel

import android.telecom.Conference
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appplatzi.conf.network.Callback
import com.appplatzi.conf.network.FirestoreSevice
import java.lang.Exception

class ScheduleViewModel: ViewModel(){
    val firestoreService = FirestoreSevice()
    var listSchedule:MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh (){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object:Callback<List<Conference>>  {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
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