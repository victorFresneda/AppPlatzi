package view.adapter


import com.appplatzi.conf.model.Speaker


interface SpeakerListener{
    fun onSpeakerClicked(speaker: Speaker, position: Int)

}