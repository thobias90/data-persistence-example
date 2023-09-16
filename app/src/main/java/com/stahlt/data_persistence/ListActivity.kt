package com.stahlt.data_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.stahlt.data_persistence.adapter.MyAdapter
import com.stahlt.data_persistence.database.DatabaseHandler
import com.stahlt.data_persistence.entity.Student

class ListActivity : AppCompatActivity() {
    private lateinit var database: DatabaseHandler
    private lateinit var lvRegisters: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        database = DatabaseHandler(this)
        val cursor = database.listCursor()

        val adapter = MyAdapter(this, cursor)

        lvRegisters = findViewById(R.id.lvRegisters)
        lvRegisters.adapter = adapter
    }

    fun getNameArrayList(registers: MutableList<Student>): MutableList<String> {
        val nameList = mutableListOf<String>()
        for (register in registers) {
            nameList.add(register.name)
        }
        return nameList
    }
}