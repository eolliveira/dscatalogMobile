package com.example.dscatalogmobile.gui.activity

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.Retrofit.ApiClient
import com.example.dscatalogmobile.gui.adapter.CatalogoProdutosAdapter
import com.example.dscatalogmobile.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class CatalogoProdutosActivity : BaseActivity() {


    private var listaProdutos: MutableList<Produto> = emptyList<Produto>().toMutableList()
    private val scope = CoroutineScope(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_produtos)

        val recyclerView =
            findViewById<RecyclerView>(R.id.activity_catalogo_produtos_lista_produtos)

        val adapter = CatalogoProdutosAdapter(
            this, listaProdutos
        )
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)


        scope.launch {

            val service = ApiClient().produtoService

            val call = service.findAll()

            val response = call!!.execute()

            if (response.isSuccessful) {




                var list = response.body()?.map { p -> Produto(p) }

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
}