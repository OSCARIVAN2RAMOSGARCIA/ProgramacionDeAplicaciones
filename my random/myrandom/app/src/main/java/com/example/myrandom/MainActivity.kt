package com.example.myrandom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import java.util.Random  //Libreria para la funcion random
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //salida de pantalla
        val r=findViewById<TextView>(R.id.resultado)
        val mini=findViewById<TextView>(R.id.txtmini)
        val maxa=findViewById<TextView>(R.id.txtmaxa)
        val prome=findViewById<TextView>(R.id.txtprome)
        //declaracion de boton

        var btnRandom=findViewById<Button>(R.id.btnRandom) as Button

        btnRandom.setOnClickListener{

            var promedio = 0

            var sumatoria = 0
            val listnum:ArrayList<Int> = arrayListOf<Int>() //declracion del array como una lista de enteros
            //ciclo para añadir los aleatorios en el vector
            for (i in 0 until 10) {
                //Numeros aleatorios
               fun Random.nextInt(range: IntRange): //declaras la funcion
                        Int {
                    return range.start + nextInt(range.last - range.start) //estructura del valor que regresara
                }
                val rand = Random()
                var num=rand.nextInt(1..100)//funcion random en una variable
                listnum.add(num)  //agrega los numeros aleatorios del rango 1 al 100
                sumatoria=sumatoria+listnum[i]    //suma todos los valores aleatorios del arreglo
                promedio=sumatoria/10           //promedia entre 10 el los valores del arreglo
            }
            //Valor mayor de el Array
            var min=listnum[0]
            var minimo=min
            for (a in 0 until 10){
                if (listnum[a]>=minimo){
                    minimo=listnum[a]
                }
            }
            //Obtener el valor mayor de el Array
            var maximo=minimo
            //Obtener el valor menor de el Array
            for (b in 0 until 10) {
                if (listnum[b]<minimo) {
                    minimo=listnum[b]
                }
            }
//imprimir los valores finales
            r.setText("Valores del Aleatorios:"+listnum)
            mini.setText("Valor minimo:"+minimo)
            maxa.setText("Valor maximo:"+maximo)
            prome.setText("Promedio:"+promedio)
        }

        }


}