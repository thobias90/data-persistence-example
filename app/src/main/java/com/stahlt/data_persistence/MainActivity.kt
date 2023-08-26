package com.stahlt.data_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btAddOnClick(view: View) {}
    fun btChangeOnClick(view: View) {}
    fun btDeleteOnClick(view: View) {}
    fun btSearchOnClick(view: View) {}
    fun btListOnClick(view: View) {}
}