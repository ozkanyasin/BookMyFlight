package com.example.bookmyflight.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyflight.R
import com.example.bookmyflight.databinding.ItemFlightBinding
import com.example.bookmyflight.mode.Flight


class FlightListAdapter(val flightList: ArrayList<Flight>)  :
    RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder>(){



        class FlightListViewHolder(var view : ItemFlightBinding) :RecyclerView.ViewHolder(view.root){

        }

   /* private var flightList: ArrayList<Flight> = arrayListOf()*/
    /*constructor(listener :IFlightAdapter)*  burası yukarda implemente idi*/
    /*private var mListener : IFlightAdapter = listener*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemFlightBinding>(inflater, R.layout.item_flight,parent,false)
        return FlightListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightListViewHolder, position: Int) {

        holder.view.textScheduleTime.text =
            flightList[position].estimatedLandingTime?.substring(11,16) ?: "Currently Unavailable"

        holder.view.textFlightStates.text = flightList[position].flightDirection

        /*holder.view.textFlightStates.text = flightList[position].publicFlightState.toString()
        holder.view.textDestinations.text = flightList[position].route?.destinations.toString()
        holder.view.textEstimatedLandingTime.text = flightList[position].actualLandingTime.toString().substring(11,15)
*/

    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    fun updateFlightList(newFlightList : ArrayList<Flight>) {
        flightList.clear()
        flightList.addAll(newFlightList)
        notifyDataSetChanged()

    }


    /*interface IFlightAdapter {
        fun onItemClicked(flight: Flight)
    }*/

}