package com.stahlt.data_persistence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.stahlt.data_persistence.adapter.MyAdapter
import com.stahlt.data_persistence.database.DatabaseHandler

class ListActivity : AppCompatActivity() {
    private lateinit var database: DatabaseHandler
    private lateinit var lvRegisters: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        lvRegisters = findViewById(R.id.lvRegisters)
        database = DatabaseHandler(this)

        val cursor = database.listCursor()
        val adapter = MyAdapter(this, cursor)

        lvRegisters.adapter = adapter
    }

    override fun onRestart() {
        super.onRestart()

        val cursor = database.listCursor()
        val adapter = MyAdapter(this, cursor)

        lvRegisters.adapter = adapter
    }

    fun btAddOnClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}