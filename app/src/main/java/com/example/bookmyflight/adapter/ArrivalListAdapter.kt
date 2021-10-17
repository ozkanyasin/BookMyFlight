package com.example.bookmyflight.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyflight.R
import com.example.bookmyflight.databinding.ItemArrivalFlightBinding
import com.example.bookmyflight.mode.Flight


class ArrivalListAdapter(val arrivalFlightList: ArrayList<Flight>)  :
    RecyclerView.Adapter<ArrivalListAdapter.ArrivalListViewHolder>(){



        class ArrivalListViewHolder(var view : ItemArrivalFlightBinding) :RecyclerView.ViewHolder(view.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArrivalFlightBinding>(inflater, R.layout.item_arrival_flight,parent,false)
        return ArrivalListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArrivalListViewHolder, position: Int) {

        holder.view.textScheduleTime.text = "Schedule Time:\n"+
            arrivalFlightList[position].scheduleTime?.substring(0,5) ?: "bilinmiyor"
        holder.view.textRoute.text = "Destination:\n" + arrivalFlightList[position].route?.destinations.toString()
        holder.view.textactualLandingTime.text = "Landing Time:\n" +
            arrivalFlightList[position].actualLandingTime?.substring(11,16)
                if (arrivalFlightList[position].actualLandingTime == null)
                    holder.view.textactualLandingTime.text = "Landing Time:\nUnscheduled"
        holder.view.textFlightState.text = "Flight State:\n" + arrivalFlightList[position].publicFlightState?.flightStates.toString()
        holder.view.textAirlineName.text = arrivalFlightList[position].airlineName?.publicName ?: "bilinmiyor"
        holder.view.textFlightName.text = "Flight Name: "+ arrivalFlightList[position].flightName


    }

    override fun getItemCount(): Int {
        return arrivalFlightList.size
    }


    fun updateArrivalList(newFlightList : ArrayList<Flight>) {
        arrivalFlightList.clear()
        arrivalFlightList.addAll(newFlightList)
        notifyDataSetChanged()

    }


    /*interface IFlightAdapter {
        fun onItemClicked(flight: Flight)
    }*/

}