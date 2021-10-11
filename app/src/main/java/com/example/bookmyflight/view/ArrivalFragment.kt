package com.example.bookmyflight.view

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmyflight.R
import com.example.bookmyflight.adapter.FlightListAdapter
import com.example.bookmyflight.databinding.FragmentArrivalBinding
import com.example.bookmyflight.service.IService
import com.example.bookmyflight.service.repository.FlightRepository
import com.example.bookmyflight.viewmodel.ArrivalViewModel
import com.example.bookmyflight.viewmodel.MyViewModelFactory


class ArrivalFragment : Fragment() {

    private lateinit var viewModel : ArrivalViewModel
    private lateinit var binding: FragmentArrivalBinding
    private val flightListAdapter = FlightListAdapter(arrayListOf())
    private val flightService = IService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_arrival,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel = ViewModelProvider(this, MyViewModelFactory(FlightRepository(flightService))).get(ArrivalViewModel::class.java)
        viewModel.getFlightsByDirection("A")

        binding.arrivalListRv.layoutManager = LinearLayoutManager(context)
        binding.arrivalListRv.adapter = flightListAdapter

        observeLiveData()

    }



    fun observeLiveData() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.arrivalListRv.visibility = View.VISIBLE
                flightListAdapter.updateFlightList(it.flights!!)
                binding.arrivalLoading.visibility = View.GONE
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