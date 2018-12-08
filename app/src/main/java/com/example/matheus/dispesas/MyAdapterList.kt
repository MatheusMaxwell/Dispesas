package com.example.matheus.dispesas

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyAdapterList(private val context: Context, private val list: List<Entities.Dispesa>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if (view == null) {
            view = View.inflate(context, R.layout.activity_my_adapter_list, null)
        }

        val dispesa = list[position]

        var txtDescricao = view!!.findViewById<TextView>(R.id.txtDescricao)
        var txtTipo = view!!.findViewById<TextView>(R.id.txtTipo)
        var txtValor = view!!.findViewById<TextView>(R.id.txtValor)
        var txtData = view!!.findViewById<TextView>(R.id.txtData)


        txtDescricao.setText(dispesa.descricao)
        txtTipo.setText(dispesa.tipo)
        txtValor.setText(dispesa.valor.toString())
        txtData.setText(dispesa.data)

        return  view
    }


}
