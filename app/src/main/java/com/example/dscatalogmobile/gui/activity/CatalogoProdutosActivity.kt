package com.example.dscatalogmobile.gui.activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
import java.lang.Exception

class CatalogoProdutosActivity : BaseActivity() {

    companion object {
        private const val PRODUTO = "PRODUTO"
    }

    //private val PRODUTO: String = "PRODUTO"
    private var listaProdutos: MutableList<Produto> = emptyList<Produto>().toMutableList()
    private val scope = CoroutineScope(Dispatchers.IO)

    private val botao_adicionar: FloatingActionButton by lazy { findViewById(R.id.activity_catalogo_produtos_fab_adicionar) }

    @RequiresApi(Build.VERSION_CODES.O)

    //implmentar busca de imagens de produtos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_produtos)

        botao_adicionar.setOnClickListener(this)

        val recyclerView =
            findViewById<RecyclerView>(R.id.activity_catalogo_produtos_lista_produtos)

        val adapter = CatalogoProdutosAdapter(
            this,
            listaProdutos,
            object : CatalogoProdutosAdapter.ItemOnClickListener {
                override fun onItemClicked(view: View, position: Int, item: Produto) {
                    val i =
                        Intent(
                            this@CatalogoProdutosActivity,
                            DetalheProdutoActivity::class.java
                        )
                    i.putExtra(PRODUTO, item.id)
                    startActivity(i)
                }
            })

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        try {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Carregando produtos...")

            scope.launch {
                //withContext(Dispatchers.Main) { progressDialog.show() }

                val service = ApiClient().produtoService
                val call = service.findAll()
                val response = call!!.execute()

                ///

                if (response.isSuccessful) {
                    var list = response.body()?.getContent()?.map { p -> Produto(p) }

                    listaProdutos.clear()
                    if (list != null) {
                        listaProdutos.addAll(list)
                    }

                    //withContext(Dispatchers.Main) { progressDialog.cancel() }

                    withContext(Dispatchers.Main) {
                        adapter.atualiza(listaProdutos)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@CatalogoProdutosActivity,
                            "produtos não encontrados",
                            Toast.LENGTH_LONG
                        )
                    }

                    try {
                        throw Exception(response.message())
                    } catch (e: Exception) {
                        AlertDialog.Builder(
                            this@CatalogoProdutosActivity
                        )
                            .setTitle("Error")
                            .setMessage("error: " + e.message)
                            .show()
                    }
                }
            }

        } catch (e: Exception) {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("error: " + e.message)
                .show()

            Log.i("TESTE", "onCreate: " + e.message)
        }
    }


    override fun onClick(view: View?) {
        when (view) {
            botao_adicionar -> startActivity(Intent(this, CadastroProdutoActivity::class.java))
        }
    }
}