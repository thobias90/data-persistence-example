package com.stahlt.data_persistence.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.stahlt.data_persistence.R
import com.stahlt.data_persistence.entity.Student

private const val NAME_INDEX = 1

private const val PHONE_INDEX = 2

private const val ID_INDEX = 0

class MyAdapter(private val context: Context, private val cursor: Cursor): BaseAdapter() {
    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(id: Int): Any {
        cursor.moveToPosition(id)
        return Student(
            cursor.getInt(ID_INDEX),
            cursor.getString(NAME_INDEX),
            cursor.getString(PHONE_INDEX))
    }

    override fun getItemId(id: Int): Long {
        cursor.moveToPosition(id)
        return cursor.getInt(ID_INDEX).toLong()
    }

    override fun getView(id: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listElement = inflater.inflate(R.layout.list_element, null)

        val tvNameListElement = listElement.findViewById<TextView>(R.id.tvNameListElement)
        val tvPhoneListElement = listElement.findViewById<TextView>(R.id.tvPhoneListElement)

        cursor.moveToPosition(id)

        tvNameListElement.text = cursor.getString(NAME_INDEX)
        tvPhoneListElement.text = cursor.getString(PHONE_INDEX)

        return listElement
    }
}