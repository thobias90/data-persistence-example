package com.stahlt.data_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.stahlt.data_persistence.database.DatabaseHandler
import com.stahlt.data_persistence.entity.Student

class MainActivity : AppCompatActivity() {
    private lateinit var etCode: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btDelete: Button
    private lateinit var btSearch: Button
    private lateinit var database: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCode = findViewById(R.id.etCode)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btSearch = findViewById(R.id.btSearch)
        btDelete = findViewById(R.id.btDelete)

        if (intent.getIntExtra("code", 0) != 0) {
            etCode.setText(intent.getIntExtra("code", 0).toString())
            etName.setText(intent.getStringExtra("name").toString())
            etPhone.setText(intent.getStringExtra("phone").toString())
        } else {
            btSearch.visibility = View.INVISIBLE
            btDelete.visibility = View.INVISIBLE
        }

        database = DatabaseHandler(this)
    }

    fun btSaveOnClick(view: View) {
        if (etCode.text.isEmpty()) { // Add element
            val student = Student(
                0,
                etName.text.toString(),
                etPhone.text.toString())
            database.create(student)
            Toast.makeText(this, "Element was added successfully",
                Toast.LENGTH_SHORT).show()
        } else { // Change element
            val student = Student(
                etCode.text.toString().toInt(),
                etName.text.toString(),
                etPhone.text.toString())
            database.update(student)
            Toast.makeText(this, "Element Changed", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
    fun btDeleteOnClick(view: View) {
        val code = etCode.text.toString().toInt()
        database.delete(code)
        Toast.makeText(this, "Element Deleted", Toast.LENGTH_SHORT).show()
        finish()
    }
    fun btSearchOnClick(view: View) {
        val etCodeSearch = EditText(this)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Code")
        builder.setView(etCodeSearch)
        builder.setCancelable(false)
        builder.setNegativeButton("Close", null)
        builder.setPositiveButton("Search") { _, _ ->
            val code = etCodeSearch.text.toString().toInt()
            val student: Student? = database.read(code)
            if (student != null) {
                etCode.setText(code.toString())
                etName.setText(student.name)
                etPhone.setText(student.phone)
            } else {
                Toast.makeText(this, "Register not found", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }
}