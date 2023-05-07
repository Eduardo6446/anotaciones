package com.example.appanotaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appanotaciones.databinding.ActivityMainBinding
import com.example.appanotaciones.model.Anotacion

class activity_main : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityMainBinding

    private lateinit var anotacionAdapter:adapter_anotacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = mutableListOf(
            Anotacion(1,"Hamlet Gatito"),
            Anotacion(2,"XDXD"),
            Anotacion(3,"ZZZZ")
        )
        anotacionAdapter = adapter_anotacion(data,this)
        binding.rvAnotaciones.apply {
            layoutManager=LinearLayoutManager(this@activity_main)
            adapter=anotacionAdapter
        }

        binding.btnAgregar.setOnClickListener {
            if(binding.tvDescripcionTarea.text.toString().isNotBlank()){
                val anota = Anotacion((anotacionAdapter.itemCount + 1).toLong(),
                    binding.tvDescripcionTarea.text.toString().toString())
                addAnotacion(anota)
                binding.tvDescripcionTarea.text?.clear()
            }
        }
    }

    private fun addAnotacion(anota: Anotacion) {

        anotacionAdapter.add(anota)
    }

    private fun deleteAnotacion(anota: Anotacion) {
        anotacionAdapter.remove(anota)

    }
    override fun OnClick(anota: Anotacion) {
        deleteAnotacion(anota)

    }
}