package com.example.matheus.dispesas

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_new_dispesa.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<ListView>(R.id.listDispesas)
        val listDispesas = MyApplication.database!!.DispesaDAO().getAllDispesas()
        updateListView(list, listDispesas)

        btnFilter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val viewDialog = layoutInflater.inflate(R.layout.layout_dialog_filter, null)
            val tipo = viewDialog.findViewById<Spinner>(R.id.spTipo)
            val pago = viewDialog.findViewById<Spinner>(R.id.spPago)
            val chkData = viewDialog.findViewById<CheckBox>(R.id.chkData)
            val txtData1 = viewDialog.findViewById<TextView>(R.id.txtData1)
            val txtData2 = viewDialog.findViewById<TextView>(R.id.txtData2)
            val txtAte= viewDialog.findViewById<TextView>(R.id.txtAte)


            var list = ArrayList<String>()
            list.add("Combustível")
            list.add("Alimento")
            list.add("Lazer")
            list.add("Outros")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            tipo.adapter = adapter

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
            var edtDes = viewNewDispesa.findViewById<EditText>(R.id.edtDesc)
            var spTipo = viewNewDispesa.findViewById<Spinner>(R.id.spTipo)
            var edtValor = viewNewDispesa.findViewById<EditText>(R.id.edtValor)
            var txtData = viewNewDispesa.findViewById<TextView>(R.id.txtDataNew)
            val builder = AlertDialog.Builder(this)

            returnDate(txtData)

            txtData.setOnClickListener{
                returnDateClick(txtData)
            }

            var list = ArrayList<String>()
            list.add("Combustível")
            list.add("Alimento")
            list.add("Lazer")
            list.add("Outros")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            spTipo.adapter = adapter

            builder
                .setTitle("Nova dispesa")
                .setView(viewNewDispesa)
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                    if(edtDes.text.toString().isNullOrEmpty() || edtValor.text.toString().isNullOrEmpty()){
                        showMenssageDialog("Valores em branco", this, viewNewDispesa)
                    }
                    else{
                        val dispesa = Entities.Dispesa(edtDes.text.toString(), spTipo.selectedItem.toString(), edtValor.text.toString().toFloat(), false, txtData.text.toString())
                        MyApplication.database!!.DispesaDAO().insertDispesa(dispesa)
                        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

                    }
                    return@OnClickListener
                }).setNegativeButton(android.R.string.cancel) { dialog, which ->
                    dialog.dismiss()
                }

            builder.create().show()

        }


    }


    fun showMenssageDialog(msg: String, context: Context, view: View){
        val buider = AlertDialog.Builder(context)

        buider.setTitle("Alerta!")
            .setMessage(msg)
            .setView(view)
            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                return@OnClickListener
            })

        buider.create().show()
    }

    fun returnDateClick(txt: TextView){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
            var month1 = mMonth +1
            txt!!.setText(""+mDay+"/"+month1+"/"+mYear)
            //Toast.makeText(context, "date: "+dateBorrowInverse,Toast.LENGTH_LONG).show()
        }, year, month, day)
        dpd.show()

    }

    fun returnDate(txt: TextView){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH) + 1
        val year = c.get(Calendar.YEAR)

        txt.setText(""+day+"/"+month+"/"+year)
    }

    fun updateListView (listView: ListView, listAdapter: List<Entities.Dispesa>){
        val adapter = MyAdapterList(this, listAdapter)
        listView.adapter = adapter
    }
}
