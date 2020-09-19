package view.adapter

import com.appplatzi.conf.model.Conference


interface ScheduleListener{
    fun onConferenceClicked(conference: Conference, position: Int)
}