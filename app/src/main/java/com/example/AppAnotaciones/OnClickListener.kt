package com.example.AppAnotaciones

import com.example.AppAnotaciones.model.Anotacion

interface OnClickListener {

    fun onChecked(anotacion: Anotacion){

    }

    fun onClick(anotacion: Anotacion, adapters: adapter_anotacion){

    }
}