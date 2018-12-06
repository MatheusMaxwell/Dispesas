package com.example.matheus.dispesas

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnFilter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val viewDialog = layoutInflater.inflate(R.layout.layout_dialog_filter, null)
            val tipo = viewDialog.findViewById<Spinner>(R.id.spTipo)
            val pago = viewDialog.findViewById<Spinner>(R.id.spPago)
            val chkData = viewDialog.findViewById<CheckBox>(R.id.chkData)
            val txtData1 = viewDialog.findViewById<TextView>(R.id.txtData1)
            val txtData2 = viewDialog.findViewById<TextView>(R.id.txtData2)
            val txtAte= viewDialog.findViewById<TextView>(R.id.txtAte)

            chkData.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    txtAte.visibility = View.VISIBLE
                    txtData1.visibility = View.VISIBLE
                    txtData2.visibility = View.VISIBLE
                }
                else{
                    txtAte.visibility = View.INVISIBLE
                    txtData1.visibility = View.INVISIBLE
                    txtData2.visibility = View.INVISIBLE
                }
            }

            builder
                .setTitle("Filtrar")
                .setView(viewDialog)
                .setPositiveButton("Filtrar", DialogInterface.OnClickListener { dialog, which ->
                    return@OnClickListener
                }).setNegativeButton(android.R.string.cancel) { dialog, which ->
                    dialog.dismiss()
                }

            builder.create().show()

        }

        btnNew.setOnClickListener {

            val viewNewDispesa = layoutInflater.inflate(R.layout.layout_new_dispesa, null)

        }

    }
}
