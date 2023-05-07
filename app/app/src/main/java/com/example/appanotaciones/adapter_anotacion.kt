package com.example.appanotaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appanotaciones.databinding.ItemNotasBinding
import com.example.appanotaciones.model.Anotacion

class adapter_anotacion(
    var anotacionList: MutableList<Anotacion>,
    private val Listener: activity_main
):
    RecyclerView.Adapter<adapter_anotacion.ViewHolder>(){

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val binding = ItemNotasBinding.bind(view)

        //Eliminar el item de la lista
        fun setListener(anota:Anotacion)
        {
            binding.root.setOnClickListener {
                Listener.OnClick(anota)
                true
            }
        }

        //------------

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notas,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =anotacionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val anotacion = anotacionList.get(position)
        holder.setListener(anotacion)
        holder.binding.tvTarea.text = anotacion.tarea
    }

    fun add(anota: Anotacion) {
        anotacionList.add(anota)
        notifyDataSetChanged()
    }

    fun remove(anota: Anotacion) {
        anotacionList.remove(anota)
        notifyDataSetChanged()
    }
}