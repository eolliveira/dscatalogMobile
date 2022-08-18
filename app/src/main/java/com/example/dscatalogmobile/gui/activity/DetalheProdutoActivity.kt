package com.example.dscatalogmobile.gui.activity

import android.app.AlertDialog
import android.media.metrics.LogSessionId
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.load
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.Retrofit.ApiClient
import com.example.dscatalogmobile.model.Produto
import com.example.dscatalogmobile.utils.FormatCurrency
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
    private val campo_descricao: TextView by lazy { findViewById(R.id.activity_detalhe_produto_descricao) }
    private val imagem_produto: ImageView by lazy { findViewById(R.id.activity_detalhe_produto_img) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_produto)
        setTitle("Detalhes do Produto")

//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            val inflater: MenuInflater = menuInflater
//            inflater.inflate(R.menu.game_menu, menu)
//            return true
//        }
//


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
                        campo_preco.text = FormatCurrency.real(produto.price)
                        campo_descricao.text = produto.description
                        imagem_produto.load(produto.imgUrl)
                    }
                }


            }
        }


    }


    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}