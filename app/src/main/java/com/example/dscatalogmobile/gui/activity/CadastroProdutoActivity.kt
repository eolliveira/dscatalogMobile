package com.example.dscatalogmobile.gui.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.dscatalogmobile.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CadastroProdutoActivity : BaseActivity() {

    private val botao_salvar: Button by lazy { findViewById(R.id.activity_cadastro_produto_btn_salvar) }
    private val botao_cancelar: Button by lazy { findViewById(R.id.activity_cadastro_produto_btn_cancelar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)


        botao_cancelar.setOnClickListener(this)
        botao_salvar.setOnClickListener(this)



        setTitle("Cadastro de Produto")


        val teste = findViewById<AutoCompleteTextView>(R.id.campo_teste)


        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        val adapter = ArrayAdapter(this, R.layout.list_item, items)

        teste.setAdapter(adapter)


    }

    override fun onClick(view: View?) {
        when(view){
            botao_cancelar -> finish()
        }
    }
}