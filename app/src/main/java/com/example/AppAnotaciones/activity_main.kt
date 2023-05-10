package com.example.AppAnotaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appanotificaciones.databinding.ActivityMainBinding
import com.example.AppAnotaciones.model.Anotacion
import com.example.appanotificaciones.R

class activity_main : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var anotacionAdapter: adapter_anotacion
    private lateinit var anotacionAdapterF: adapter_anotacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        anotacionAdapter = adapter_anotacion(mutableListOf(), this)
        binding.rvTareasPendientes.apply { layoutManager = LinearLayoutManager(this@activity_main)
        adapter = anotacionAdapter
        }

        //Tareas finalizaadas
        anotacionAdapterF = adapter_anotacion(mutableListOf(), this)
        binding.rvTareasFinalizadas.apply { layoutManager = LinearLayoutManager(this@activity_main)
            adapter = anotacionAdapterF
        }

        binding.btnAgregar.setOnClickListener {
            if(binding.tvDescripcionTarea.text.toString().isNotBlank()){
                val anota = Anotacion((anotacionAdapter.itemCount + 1).toLong(),
                                       binding.tvDescripcionTarea.text.toString().trim())
                addAnotacion(anota)
                binding.tvDescripcionTarea.text?.clear()
            }else
            {
                binding.tvDescripcionTarea.error = getString(R.string.strValidacionError)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData(){
        val data = mutableListOf(
            Anotacion(1,"Hamlet gatito"),
            Anotacion(2,"edu"),
            Anotacion(3,"kevin"),
             true
        )
        data.forEach { Anotacionn ->
            addAnotacion(Anotacionn as Anotacion)
        }
    }

    private fun addAnotacion(anota: Anotacion) {
        if (anota.Finalizado)
        {
            anotacionAdapterF.add(anota)
        }
        else
        {
            anotacionAdapter.add(anota)
        }
    }

    private fun deleteAnotacion(anota: Anotacion) {
        if (anota.Finalizado)
        {
            anotacionAdapter.remove(anota)
        }
        else
        {
            anotacionAdapterF.remove(anota)
        }
    }

    override fun onClick(anota: Anotacion, adapters: adapter_anotacion){
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.strDialogoTitulo))
            .setPositiveButton(getString(R.string.strAceptar),{
                dialogInterface, i ->
                adapters.remove(anota)
        })
            .setNegativeButton(getString(R.string.strCancelar), null)
        builder.create().show()
    }

    override fun onChecked(anotacion: Anotacion) {
        deleteAnotacion(anotacion)
        addAnotacion(anotacion)
    }
}

