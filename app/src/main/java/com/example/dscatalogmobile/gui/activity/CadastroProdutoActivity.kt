package com.example.dscatalogmobile.gui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.Retrofit.ApiClient
import com.example.dscatalogmobile.model.Categoria
import com.example.dscatalogmobile.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class CadastroProdutoActivity : BaseActivity() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var listaCategorias: MutableList<Categoria> = emptyList<Categoria>().toMutableList()

    private val campo_nome: EditText by lazy { findViewById(R.id.activity_cadastro_produto_edt_nome) }
    private val campo_preco: EditText by lazy { findViewById(R.id.activity_cadastro_produto_edt_preco) }
    private val campo_categoria: EditText by lazy { findViewById(R.id.activity_cadastro_produto_edt_categoria) }
    private val campo_descricao: EditText by lazy { findViewById(R.id.activity_cadastro_produto_edt_descricao) }

    private val botao_adicionar_img: Button by lazy { findViewById(R.id.activity_cadastro_produto_btn_adicionar_img) }

    private val botao_salvar: Button by lazy { findViewById(R.id.activity_cadastro_produto_btn_salvar) }
    private val botao_cancelar: Button by lazy { findViewById(R.id.activity_cadastro_produto_btn_cancelar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        botao_cancelar.setOnClickListener(this)
        botao_salvar.setOnClickListener(this)
        botao_adicionar_img.setOnClickListener(this)

        setTitle("Cadastro de Produto")


        scope.launch {
            val service = ApiClient().categoriaService
            val call = service.findAll()
            val response = call!!.execute()
            if (response.isSuccessful) {
                var list = response.body()?.getContent()?.map { p -> Categoria(p) }

                listaCategorias.clear()
                if (list != null) {
                    listaCategorias.addAll(list)
                }

                withContext(Dispatchers.Main) {
                    val dCaategoria = findViewById<AutoCompleteTextView>(R.id.activity_cadastro_produto_edt_categoria)
                    listaCategorias.map { c -> c.name }

                    val adapter = ArrayAdapter(
                        this@CadastroProdutoActivity,
                        R.layout.list_item,
                        listaCategorias.map { c -> c.name.toString() })

                    dCaategoria.setAdapter(adapter)

                }
            }
        }


    }

    override fun onClick(view: View?) {
        when (view) {
            botao_cancelar -> finish()
            botao_salvar -> SalvarProduto()
            botao_adicionar_img -> carregaImagem()
        }
    }

    private fun carregaImagem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this)
                .setView(R.layout.activity_cadastro_produto_carrega_img_dialog)
                .setPositiveButton("Confirmar") { _, _ ->


                }
                .setNegativeButton("Cancelar") { _, _ ->


                }
                .show()
        }


    }

    private fun SalvarProduto() {
        scope.launch {


            val nome = campo_nome.text

            val precoStr = campo_preco.text.toString()
            val preco: Double = precoStr.toDouble()

            val categoria = campo_categoria.text
            val descricao = campo_descricao.text


            //produto
            val produto = Produto(null, nome.toString(), descricao.toString(), preco, null, null)

            val service = ApiClient().produtoService
            val call = service.adiciona(produto)
            val response = call!!.execute()
            if (response.isSuccessful ) {

                Log.i("TESTESALVAR", "SalvarProduto: Salvouuuu ")
                finish()

            }







            }
    }
}