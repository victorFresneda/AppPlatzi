package view.ui.fragments

import android.os.Bundle
import android.telecom.Conference
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.appplatzi.conf.R
import kotlinx.android.synthetic.main.fragment_schedule.*
import view.adapter.ScheduleAdapter
import viewmodel.ScheduleViewModel

/**
 * A simple [Fragment] subclass.
 */
class EscheduleFragment : Fragment() {

    private lateinit var schededuleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        schededuleAdapter = ScheduleAdapter(this)

        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listSchedule.observe(this, Observer <List<Conference>>{ schedule ->
            schededuleAdapter.updateData(schedule)
        })
    }


}
