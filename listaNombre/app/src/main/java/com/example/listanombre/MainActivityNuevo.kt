package com.example.listanombre

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_nuevo.*

class MainActivityNuevo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nuevo)

        val intExtra=intent.extras
        val idAlumno=intExtra?.getInt("idAlumno")
        if (idAlumno!=null){
            titOpcNuevo.text="Editar alumno"
            Toast.makeText(this,"Opcion editar",Toast.LENGTH_LONG).show()


        }

        //abrimos la base de datos
        val dbAlumno=DBHelperAlumnos(this)


        btnGuardar.setOnClickListener {
            val db=dbAlumno.writableDatabase

            val nombre=txtNombre.text.toString()
            val cuenta=txtCuenta.text.toString()
            /*
             //Alternativa 1

             val sql="INSERT INTO alumnos (name,noCuenta)VALUES ('$nom','$cuenta')"
             val status=db.execSQL(sql)
             */

            //Alternativa 2
            val newReg= ContentValues()
            newReg.put("nombre",nombre)
            newReg.put("cuenta",Integer.parseInt(cuenta))

            //Insertamos el registro
            val res=db.insert("alumno",null,newReg)
            db.close()
            if (res.toInt() == -1){
                Toast.makeText(this,"Datos incompletos, registro sin guardar",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Registro guardado con exito",Toast.LENGTH_LONG).show()
                txtNombre.text.clear()
                txtCuenta.text.clear()

                val lista= Intent(this,MainActivity::class.java)
                startActivity(lista)
            }
        }

    }

}