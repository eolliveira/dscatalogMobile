package com.example.dscatalogmobile.gui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.dscatalogmobile.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CadastroProdutoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)


        setTitle("Cadastro de Produto")


//        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
//        val adapter = ArrayAdapter(this, R.layout.list_item, items)
//        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)


        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        (textField.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

    }
}