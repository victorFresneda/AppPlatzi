package com.appplatzi.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.appplatzi.conf.R
import com.appplatzi.conf.model.Conference
import com.appplatzi.conf.model.Speaker
import com.appplatzi.conf.view.adapter.ScheduleAdapter
import com.appplatzi.conf.view.adapter.ScheduleListener
import com.appplatzi.conf.view.adapter.SpeakerAdapter
import com.appplatzi.conf.view.adapter.SpeakerListener
import com.appplatzi.conf.viewmodel.ScheduleViewModel
import com.appplatzi.conf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment(), SpeakerListener {




        private lateinit var speakerAdapter: SpeakerAdapter
        private lateinit var viewModel: SpeakersViewModel


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_speakers, container,false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
            viewModel.refresh()

            speakerAdapter = SpeakerAdapter (this )

            rvSpeakers.apply{
                layoutManager = GridLayoutManager(context, 2)
                adapter = speakerAdapter
            }
            observeViewModel()
        }
        fun observeViewModel(){viewModel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>>{ speakers ->
            speakerAdapter.updateData(speakers)
        })
            viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
                if (it != null)
                    rlBaseSchedule.visibility = View.INVISIBLE

            })


        }

        override fun onConferenceClicked(speaker: Speaker, position: Int) {
            val bundle = bundleOf("speaker" to speaker)
            findNavController().navigate(R.id.speakersDetailFragmentsDialog, bundle)
        }


    }


