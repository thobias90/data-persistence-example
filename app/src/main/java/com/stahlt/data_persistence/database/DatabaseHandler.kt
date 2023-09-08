package com.stahlt.data_persistence.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.stahlt.data_persistence.entity.Student
import java.lang.StringBuilder

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbfile.sqlite"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "students"
        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME ($KEY_ID INTEGER " +
                "PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT, $KEY_PHONE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_NAME")
        onCreate(db)
    }

    fun create(student: Student) {
        val db = this.writableDatabase
        val register = ContentValues()
        register.put(KEY_NAME, student.name)
        register.put(KEY_PHONE, student.phone)
        db.insert(TABLE_NAME, null, register)
    }

    fun read(id: Int): Student? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$KEY_ID=$id",
            null, null, null, null)
        var student: Student? = null
        cursor.run {
            if (moveToNext()) {
                student = Student(id, getString(1), getString(2))
            }
            close()
        }
        return student
    }
    fun update(student: Student) {
        val db = this.writableDatabase
        val id = student.id
        val register = ContentValues()
        register.put(KEY_NAME, student.name)
        register.put(KEY_PHONE, student.phone)
        db.update(TABLE_NAME, register, "$KEY_ID=$id", null)
    }

    fun delete(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=$id", null)
    }

    fun list(): String {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null,
            null, null, null)
        val output = StringBuilder()
        while(cursor.moveToNext()) {
            output.append(cursor.getInt(0))
            output.append(" ")
            output.append(cursor.getString(1))
            output.append(" ")
            output.append(cursor.getString(2))
            output.append("\n")
        }
        cursor.close()
        return "$output"
    }
}