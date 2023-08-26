package com.stahlt.data_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var etCod: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
    }

    fun btAddOnClick(view: View) {}
    fun btChangeOnClick(view: View) {}
    fun btDeleteOnClick(view: View) {}
    fun btSearchOnClick(view: View) {}
    fun btListOnClick(view: View) {}
}