package com.example.bookmyflight.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyflight.R
import com.example.bookmyflight.databinding.ItemDepartureFlightBinding
import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.view.DepartureFragmentDirections

class DepartureListAdapter(val departureFlightList: ArrayList<Flight>) :
    RecyclerView.Adapter<DepartureListAdapter.DepartureListViewHolder>(){


    class DepartureListViewHolder(var view: ItemDepartureFlightBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDepartureFlightBinding>(inflater, R.layout.item_departure_flight,parent,false)
        return DepartureListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DepartureListViewHolder, position: Int) {
        holder.view.textScheduleTime.text = "Schedule Time\n"+ departureFlightList[position].scheduleTime.toString().substring(0,5)
        holder.view.textRoute.text = "Destination\n"+departureFlightList[position].route?.destinations.toString()
        holder.view.textGate.text = "GATE: "+departureFlightList[position].gate.toString()
            if (departureFlightList[position].gate == null)
                holder.view.textGate.text = "GATE:\nUnscheduled"
        holder.view.textFlightState.text = "Flight State\n"+ departureFlightList[position].publicFlightState?.flightStates.toString()
        holder.view.textAirlineName.text = departureFlightList[position].airlineCode.toString()
        holder.view.textFlightName.text = "Flight Name: "+ departureFlightList[position].flightName

        holder.itemView.setOnClickListener {
            val action = departureFlightList[position].id?.let { it1 ->
                DepartureFragmentDirections.actionDepartureFragmentToFlightDetailFragment(
                    it1
                )
            }
            if (action != null) {
                Navigation.findNavController(it).navigate(action)
            }
        }

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