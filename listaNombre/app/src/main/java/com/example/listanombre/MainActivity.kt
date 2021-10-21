package com.example.listanombre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val personas = mutableListOf<String>()
        //abrir base de datos
        val dbalumnos = DBHelperAlumnos(this)
        val db = dbalumnos.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM alumno", null)
        if (cursor.moveToFirst()) {
            do {
                val itenNom = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                personas.add(itenNom)
            }while (cursor.moveToNext())
        }

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,personas)
        lvDatos.adapter=arrayAdapter

        lvDatos.setOnItemClickListener() { parent, view, position, id ->
            val intent04 = Intent(this,ActivityMainDetalle::class.java)
           //

            startActivity(intent04)
        }

        btnAdd.setOnClickListener{
            val intent01= Intent(this,MainActivityNuevo::class.java)
            startActivity(intent01)
        }
    }
}