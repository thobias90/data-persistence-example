package com.stahlt.data_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.stahlt.data_persistence.database.DatabaseHandler
import com.stahlt.data_persistence.entity.Student

class MainActivity : AppCompatActivity() {
    private lateinit var etCode: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var database: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCode = findViewById(R.id.etCode)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)

        database = DatabaseHandler(this)
    }

    fun btAddOnClick(view: View) {
        val student = Student(0, etName.text.toString(), etPhone.text.toString())
        database.create(student)
        Toast.makeText(this, "Element was added successfully",
            Toast.LENGTH_SHORT).show()
    }
    fun btChangeOnClick(view: View) {
        val student = Student(etCode.text.toString().toInt(), etName.text.toString(),
            etPhone.text.toString())
        database.update(student)
        Toast.makeText(this, "Element Changed", Toast.LENGTH_SHORT).show()
    }
    fun btDeleteOnClick(view: View) {
        val code = etCode.text.toString().toInt()
        database.delete(code)
        Toast.makeText(this, "Element Deleted", Toast.LENGTH_SHORT).show()
    }
    fun btSearchOnClick(view: View) {
        val code = etCode.text.toString().toInt()
        val student: Student? = database.read(code)
        if (student != null) {
            etName.setText(student.name)
            etPhone.setText(student.phone)
        } else {
            Toast.makeText(this, "Register not found", Toast.LENGTH_SHORT).show()
        }
    }
    fun btListOnClick(view: View) {
        val databaseList: String = database.list()
        Toast.makeText(this, databaseList, Toast.LENGTH_LONG).show()
    }
}