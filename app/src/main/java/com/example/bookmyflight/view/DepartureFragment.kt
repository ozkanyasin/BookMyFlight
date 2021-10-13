package com.example.bookmyflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmyflight.R
import com.example.bookmyflight.adapter.DepartureListAdapter
import com.example.bookmyflight.databinding.FragmentDepartureBinding
import com.example.bookmyflight.service.IService
import com.example.bookmyflight.service.repository.FlightRepository
import com.example.bookmyflight.viewmodel.DepartureViewModel
import com.example.bookmyflight.viewmodel.MyViewModelFactory


class DepartureFragment : Fragment() {

    private lateinit var viewModel : DepartureViewModel
    private lateinit var binding: FragmentDepartureBinding
    private val flightListAdapter = DepartureListAdapter(arrayListOf())
    private val flightService = IService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_departure,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel = ViewModelProvider(this, MyViewModelFactory(FlightRepository(flightService))).get(
            DepartureViewModel::class.java)
        viewModel.getFlights()

        binding.departureListRv.layoutManager = LinearLayoutManager(context)
        binding.departureListRv.adapter = flightListAdapter

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.departureListRv.visibility = View.VISIBLE
                flightListAdapter.updateDepartureList(it.flights!!)
                binding.departureLoading.visibility = View.GONE
            }
        })

        /* viewModel.flightLoading.observe(viewLifecycleOwner, Observer {
             it?.let {
                 if (it){
                     binding.arrivalListRv.visibility = View.VISIBLE
                     binding.arrivalListRv.visibility = View.GONE
                 }else{
                     binding.arrivalListRv.visibility = View.GONE
                 }
             }
         })*/
    }


}