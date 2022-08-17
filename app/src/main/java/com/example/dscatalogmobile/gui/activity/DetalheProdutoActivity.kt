package com.example.dscatalogmobile.gui.activity

import android.app.AlertDialog
import android.media.metrics.LogSessionId
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.Retrofit.ApiClient
import com.example.dscatalogmobile.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetalheProdutoActivity : BaseActivity() {

    companion object {
        private val PRODUTO = "PRODUTO"
    }

    private val scope = CoroutineScope(Dispatchers.IO)
    private val campo_nome: TextView by lazy { findViewById(R.id.activity_detalhe_produto_nome) }
    private val campo_preco: TextView by lazy { findViewById(R.id.activity_detalhe_produto_preco) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_produto)
        setTitle("Detalhes do Produto")

        val idProduto = intent.getLongExtra(PRODUTO, 0L)
        Log.i(PRODUTO, "ProdutoId: " + idProduto)

        scope.launch {
            //withContext(Dispatchers.Main) { progressDialog.show() }

            val service = ApiClient().produtoService
            val call = service.findById(idProduto)
            val response = call!!.execute()

            //withContext(Dispatchers.Main) { progressDialog.cancel() }

            if (response.isSuccessful) {
                val produto = response.body()

                withContext(Dispatchers.Main) {
                    if (produto != null) {
                        campo_nome.text = produto.name
                        campo_preco.text = produto.price.toString()
                    }
                }


            }
        }


    }


    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}