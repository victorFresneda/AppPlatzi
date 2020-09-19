package view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.appplatzi.conf.R
import com.appplatzi.conf.model.Speaker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpeakerAdapter(val speakerListener: SpeakerListener ) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    private var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeakers.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val speaker= listSpeakers[position] as Speaker

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker, position )
        }



    }


    fun upDateData(data: List<Speaker>) {
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()

    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val tvSpeakersName = itemview.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerWork = itemview.findViewById<TextView>(R.id.tvItemSpeakerWord)
        val ivSpeakerImage = itemview.findViewById<TextView>(R.id.ivItemSpeakerImage)
    }
}