package com.example.bookmyflight.adapter

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

   /* private var flightList: ArrayList<Flight> = arrayListOf()*/
    /*constructor(listener :IFlightAdapter)*  burası yukarda implemente idi*/
    /*private var mListener : IFlightAdapter = listener*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArrivalFlightBinding>(inflater, R.layout.item_arrival_flight,parent,false)
        return ArrivalListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArrivalListViewHolder, position: Int) {

        holder.view.textScheduleTime.text =
            arrivalFlightList[position].scheduleTime?.substring(0,5) ?: "bilinmiyor"
        holder.view.textRoute.text = arrivalFlightList[position].route?.destinations.toString()
        holder.view.textactualLandingTime.text =
            arrivalFlightList[position].actualLandingTime?.substring(11,16) ?: "bilinmiyor"
        holder.view.textFlightState.text = arrivalFlightList[position].publicFlightState?.flightStates.toString()
        holder.view.textAirlineName.text = arrivalFlightList[position].airlineCode.toString()
        holder.view.textFlightName.text = arrivalFlightList[position].flightName


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