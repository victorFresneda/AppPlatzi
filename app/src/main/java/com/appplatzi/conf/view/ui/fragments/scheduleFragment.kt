package com.appplatzi.conf.view.ui.fragments

import android.os.Bundle
import android.telecom.Conference
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.appplatzi.conf.R
import kotlinx.android.synthetic.main.fragment_schedule.*
import com.appplatzi.conf.view.adapter.ScheduleAdapter
import com.appplatzi.conf.view.adapter.ScheduleListener
import com.appplatzi.conf.viewmodel.ScheduleViewModel

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var schededuleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        schededuleAdapter = ScheduleAdapter(this)

        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = schededuleAdapter

        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listSchedule.observe(this, Observer <List<Conference>>{ schedule ->
            schededuleAdapter.updateData(schedule)
        })

        viewModel.listSchedule.observe(this, Observer<Boolean>{
            if (it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenceClicked(conference: com.appplatzi.conf.model.Conference, position: Int) {
         val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentsDialog, bundle)
    }


}
