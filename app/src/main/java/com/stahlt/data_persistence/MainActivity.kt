package com.stahlt.data_persistence

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var etCode: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText

    private lateinit var dataBase: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCode = findViewById(R.id.etCode)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)

        dataBase = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath("dbfile.sqlite"),
            null)

        dataBase.execSQL("CREATE TABLE IF NOT EXISTS student (_id INTEGER " +
                "PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)")

    }

    fun btAddOnClick(view: View) {
        val register = ContentValues()
        register.put("name", etName.text.toString())
        register.put("phone", etPhone.text.toString())
        dataBase.insert("student", null, register)
        Toast.makeText(this, "Element was added successfully",
            Toast.LENGTH_SHORT).show()
    }
    fun btChangeOnClick(view: View) {
        val code = etCode.text.toString()

        val register = ContentValues()
        register.put("name", etName.text.toString())
        register.put("phone", etPhone.text.toString())
        dataBase.update("student", register, "_id=${code}", null)
        Toast.makeText(this, "Element Changed", Toast.LENGTH_SHORT).show()
    }
    fun btDeleteOnClick(view: View) {
        val code = etCode.text.toString()

        dataBase.delete("student", "_id=${code}", null)
        Toast.makeText(this, "Element Deleted", Toast.LENGTH_SHORT).show()
    }
    fun btSearchOnClick(view: View) {
        val code = etCode.text.toString()

        val registers = dataBase.query("student", null, "_id=${etCode.text}",
            null, null, null, null)
        if (registers.moveToNext()) {
            etName.setText( registers.getString(1))
            etPhone.setText(registers.getString(2))
        } else {
            Toast.makeText(this, "Register not found", Toast.LENGTH_SHORT).show()
        }

    }
    fun btListOnClick(view: View) {
        val registers = dataBase.query("student", null, null,
            null, null, null, null)
        var output = StringBuilder()
        while(registers.moveToNext()) {
            output.append(registers.getInt(0))
            output.append(" ")
            output.append(registers.getString(1))
            output.append(" ")
            output.append(registers.getString(2))
            output.append("\n")
        }
        Toast.makeText(this, output.toString(), Toast.LENGTH_LONG).show()
    }
}