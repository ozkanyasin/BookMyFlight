package com.example.bookmyflight.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyflight.R
import com.example.bookmyflight.databinding.ItemDepartureFlightBinding
import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.model.Airline

class DepartureListAdapter(val departureFlightList: ArrayList<Flight>) :
    RecyclerView.Adapter<DepartureListAdapter.DepartureListViewHolder>() {


    class DepartureListViewHolder(var view: ItemDepartureFlightBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDepartureFlightBinding>(inflater, R.layout.item_departure_flight,parent,false)
        return DepartureListAdapter.DepartureListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartureListViewHolder, position: Int) {
        holder.view.textScheduleTime.text = departureFlightList[position].scheduleTime.toString().substring(0,5)
        holder.view.textRoute.text = departureFlightList[position].route?.destinations.toString()
        holder.view.textGate.text = departureFlightList[position].gate.toString()
        holder.view.textFlightState.text = departureFlightList[position].publicFlightState?.flightStates.toString()
        holder.view.textAirlineName.text = departureFlightList[position].airlineCode.toString()
        holder.view.textFlightName.text = departureFlightList[position].flightName

    }

    override fun getItemCount(): Int {
        return departureFlightList.size
    }

    fun updateDepartureList(newDepartureList : ArrayList<Flight>) {
        departureFlightList.clear()
        departureFlightList.addAll(newDepartureList)
        notifyDataSetChanged()

    }

}