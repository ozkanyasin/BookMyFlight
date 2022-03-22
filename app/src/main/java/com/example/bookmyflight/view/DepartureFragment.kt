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
    private val flightListAdapter = DepartureListAdapter(arrayListOf())
    private val flightService = IService.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and init viewmodel
        viewModel = ViewModelProvider(this, MyViewModelFactory(FlightRepository(flightService))).get(
            DepartureViewModel::class.java)
        viewModel.binding = DataBindingUtil.inflate(inflater,R.layout.fragment_departure,container,false)
        return viewModel.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel.binding.textCurrentDate.text = viewModel.getCurrentDate()
        viewModel.scheduleDate = viewModel.getCurrentDate()
        viewModel.pickDate(viewModel.binding.imageFilterButton)
        viewModel.binding.departureListRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.departureListRv.adapter = flightListAdapter

        viewModel.binding.swipeRefreshLayoutDeparture.setOnRefreshListener {
            viewModel.binding.departureListRv.visibility = View.GONE
            viewModel.binding.departureLoading.visibility = View.VISIBLE
            viewModel.getFlights()
            viewModel.binding.swipeRefreshLayoutDeparture.isRefreshing = false
        }

       observeLiveData()
    }

    fun observeLiveData() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.binding.departureListRv.visibility = View.VISIBLE
                flightListAdapter.updateDepartureList(it.flights!!)
                viewModel.binding.departureLoading.visibility = View.GONE
            }
        })

    }


}