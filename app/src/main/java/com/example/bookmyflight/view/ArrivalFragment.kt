package com.example.bookmyflight.view

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmyflight.R
import com.example.bookmyflight.adapter.ArrivalListAdapter
import com.example.bookmyflight.databinding.FragmentArrivalBinding
import com.example.bookmyflight.service.IService
import com.example.bookmyflight.service.repository.FlightRepository
import com.example.bookmyflight.viewmodel.ArrivalViewModel
import com.example.bookmyflight.viewmodel.MyViewModelFactory


class ArrivalFragment : Fragment() {

    private lateinit var viewModel : ArrivalViewModel
    private val flightListAdapter = ArrivalListAdapter(arrayListOf())
    private val flightService = IService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view model init
        viewModel = ViewModelProvider(this,
            MyViewModelFactory(FlightRepository(flightService))).get(ArrivalViewModel::class.java)
        // Inflate the layout for this fragment
        viewModel.binding = DataBindingUtil.inflate(inflater,R.layout.fragment_arrival,container,false)
        return viewModel.binding.root
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.binding.textView2.text = viewModel.getCurrentDate()
        viewModel.scheduleDate = viewModel.getCurrentDate()
        viewModel.pickDate(viewModel.binding.imageButton)


        viewModel.binding.arrivalListRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.arrivalListRv.adapter = flightListAdapter

        observeLiveData()

    }


    fun observeLiveData() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.binding.arrivalListRv.visibility = View.VISIBLE
                flightListAdapter.updateArrivalList(it.flights!!)
                viewModel.binding.arrivalLoading.visibility = View.GONE
            }
        })

    }


}