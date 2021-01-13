package com.appplatzi.conf.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appplatzi.conf.R
import com.appplatzi.conf.model.Conference
import com.appplatzi.conf.view.adapter.ScheduleAdapter
import com.appplatzi.conf.view.adapter.ScheduleListener
import com.appplatzi.conf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_schedule, container,false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
            viewModel.refresh()

            scheduleAdapter = ScheduleAdapter(this )

            rvSchedule.apply{
                layoutManager =LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                adapter = scheduleAdapter
            }
            observeViewModel()
        }
      fun observeViewModel(){viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Conference>>{schedule ->
          scheduleAdapter.updateData(schedule)
      })
          viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
              if (it != null)
                  rlBaseSchedule.visibility = View.INVISIBLE

          })


      }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }

}