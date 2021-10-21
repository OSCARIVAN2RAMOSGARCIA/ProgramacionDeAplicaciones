package com.example.listanombre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_detalle.*
import kotlinx.android.synthetic.main.activity_main_nuevo.*

class ActivityMainDetalle : AppCompatActivity() {

    var idRegistro: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detalle)

        val io=intent.extras
        val nomAlumno=io?.getString("nomAlumno")

        val dbalumnos=DBHelperAlumnos(this)
        val db=dbalumnos.readableDatabase

        val cursor=db.rawQuery("SELECT * FROM alumno WHERE nombre='$nomAlumno'",null)

        if(cursor.moveToFirst()){
            idRegistro=cursor.getInt(0).toInt()
            var itenNom: String=cursor.getString(1)

            txtDetalleNombre.text="Nombre:'$itenNom'"
            val itenCuenta=cursor.getString(2)
            txtDetalleCuenta.text="No.Cuenta:'$itenCuenta'"
        }
        dbalumnos.close()
        cursor.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_alumno,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmEditar->{
                //Toast.makeText(this,"Click en editar",Toast.LENGTH_SHORT).show()
                var intentMod=Intent(this,MainActivityNuevo::class.java)
                intentMod.putExtra("idAlumno",idRegistro)
                startActivity(intentMod)
            }

            R.id.itm_Borrar->{
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Borrar registro")
                dialog.setMessage("¿Estás seguro que deseas eliminar el registro?")

                dialog.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(this, "Cancelo borrar!!!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                    dialog.setPositiveButton(android.R.string.yes) { dialog, which ->
                //Instrucciones para borrar
                //Abrimos base de datos

                val dbalumnos=DBHelperAlumnos(this)

                val db=dbalumnos.writableDatabase

                //eliminar registro
                db.delete("alumno","id='$idRegistro'",null)
                        Toast.makeText(this, "Registro eliminado!!!", Toast.LENGTH_SHORT).show()
                        db.close()
                        var intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}