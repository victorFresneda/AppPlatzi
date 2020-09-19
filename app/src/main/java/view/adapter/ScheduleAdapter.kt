package view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.appplatzi.conf.R
import com.appplatzi.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener ) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    var listConference = ArrayList<Conference>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val horaFormat = simpleDateFormat.format(conference.datetime)

        holder.tvConferenceHour.text = horaFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference, position)
        }

    }


    fun uoDateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()

    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val tvConferenceName: TextView = itemview.findViewById<TextView>(R.id.tvScheduleConferenceName)
        val tvConferenceSpeaker = itemview.findViewById<TextView>(R.id.tvScheduleConferenceSpeaker)
        val tvConferenceTag = itemview.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemview.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemview.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }
}