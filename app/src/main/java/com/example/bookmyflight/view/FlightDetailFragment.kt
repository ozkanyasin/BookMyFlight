package com.example.bookmyflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bookmyflight.R
import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.service.IService
import com.example.bookmyflight.service.repository.FlightRepository
import com.example.bookmyflight.viewmodel.ArrivalViewModel
import com.example.bookmyflight.viewmodel.DetailViewModel
import com.example.bookmyflight.viewmodel.MyViewModelFactory


class FlightDetailFragment : Fragment() {

    private var flightUuid = ""
    private lateinit var viewModel : DetailViewModel
    private val flightService = IService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this,
            MyViewModelFactory(FlightRepository(flightService))
        ).get(DetailViewModel::class.java)
        viewModel.binding = DataBindingUtil.inflate(inflater,R.layout.fragment_flight_detail,container,false)
        return viewModel.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            flightUuid = FlightDetailFragmentArgs.fromBundle(it).flightUuid
        }

    }

}