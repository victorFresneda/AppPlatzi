package viewmodel

import android.telecom.Conference
import androidx.lifecycle.MutableLiveData
import com.appplatzi.conf.network.FirestoreSevice

class ScheduleViewModel {

}val firestoreService = FirestoreSevice()
val listSchedule:MutableLiveData<List<Conference>> = MutableLiveData()