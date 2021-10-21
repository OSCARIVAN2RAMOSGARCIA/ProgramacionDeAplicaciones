package com.example.listanombre
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

class DBHelperAlumnos(context:Context):SQLiteOpenHelper(context,DB_Name,null,DB_Version) {

    companion object{
        private val DB_Version=1
        private val DB_Name="bdalu.db"
        private val nomTable="alumno"
        private val keyId="id"
        private val name="nombre"
        private val cuenta="cuenta"
    }
    val sqlCreate:String="CREATE TABLE $nomTable($keyId INTEGER PRIMARY KEY, $name TEXT, $cuenta INTEGER)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $nomTable")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db,oldVersion,newVersion)
    }
}

