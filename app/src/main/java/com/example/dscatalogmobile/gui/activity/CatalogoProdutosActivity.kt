package com.example.dscatalogmobile.gui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.Retrofit.ApiClient
import com.example.dscatalogmobile.gui.adapter.CatalogoProdutosAdapter
import com.example.dscatalogmobile.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogoProdutosActivity : BaseActivity() {


    private var listaProdutos: MutableList<Produto> = emptyList<Produto>().toMutableList()
    private val scope = CoroutineScope(Dispatchers.IO)


    private val botao_adicionar: FloatingActionButton by lazy { findViewById(R.id.activity_catalogo_produtos_fab_adicionar) }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_produtos)

        botao_adicionar.setOnClickListener(this)

        val recyclerView =
            findViewById<RecyclerView>(R.id.activity_catalogo_produtos_lista_produtos)

        val adapter = CatalogoProdutosAdapter(
            this, listaProdutos
        )
        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(this, 1 )

        scope.launch {
            val service = ApiClient().produtoService
            val call = service.findAll()
            val response = call!!.execute()

            if (response.isSuccessful) {

                var list = response.body()?.getContent()?.map { p -> Produto(p) }

                listaProdutos.clear()
                if (list != null) {
                    listaProdutos.addAll(list)
                }

                withContext(Dispatchers.Main) {
                    adapter.atualiza(listaProdutos)
                }

            }


        }


    }

    override fun onClick(view: View?) {
        when(view){
            botao_adicionar -> startActivity(Intent(this, CadastroProdutoActivity::class.java))
        }
    }
}