package com.cursokotlin.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList(), private val onItemSelected:(HoroscopeInfo) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    fun updateList(list: List<HoroscopeInfo>){
        horoscopeList = list
        //notificar que hicimos un cambio
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopo, parent, false)
        )
    }

    //Este metodo se encarga de decirle al viewHolder que pintar
    override fun onBindViewHolder(
        holder: HoroscopeViewHolder,
        position: Int
    ) {
        //Holder, me vas a pintar la posicion que tengo por parametro
        holder.render(horoscopeList[position], onItemSelected)
    }


    override fun getItemCount(): Int = horoscopeList.size
}