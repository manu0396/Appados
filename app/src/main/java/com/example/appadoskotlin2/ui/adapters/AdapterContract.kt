package com.example.appadoskotlin2.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.appadoskotlin2.R
import com.example.appadoskotlin2.data.Service
import com.example.appadoskotlin2.ui.contract.ContractFragment
import com.example.appadoskotlin2.ui.adapters.AdapterContract

class AdapterContract(val listener : ContractFragment,val services: ArrayList<Service>): RecyclerView.Adapter<AdapterContract.ContractHolder>() {

    //TODO()

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract, parent, false)
        return ContractHolder(itemView)
    }

    override fun getItemCount(): Int {
        return services.size
    }
    class ContractHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Service, listener: (Service) -> Unit) = with(itemView) {
            rootView.findViewById<TextView>(R.id.tvItemContract).text = item.type
            setOnClickListener { listener(item) }
        }
    }

    override fun onBindViewHolder(holder: ContractHolder, position: Int) {
        holder.bind(services[position], listener)
    }
}