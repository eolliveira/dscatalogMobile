package com.example.dscatalogmobile.gui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dscatalogmobile.R
import com.example.dscatalogmobile.model.Produto
import com.example.dscatalogmobile.utils.FormatCurrency
import java.text.NumberFormat
import java.util.*

class CatalogoProdutosAdapter(
    val context: Context,
    list: List<Produto>
) : RecyclerView.Adapter<CatalogoProdutosAdapter.ViewHolder>() {

    private val produtos = list.toMutableList()

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun vincula(produto: Produto) {
           val nome = itemView.findViewById<TextView>(R.id.activity_catalogo_produtos_nome)
           nome.text = produto.name

           val preco = itemView.findViewById<TextView>(R.id.activity_catalogo_produtos_preco)
           preco.text = FormatCurrency.real(produto.price)

           val image = itemView.findViewById<ImageView>(R.id.activity_catalogo_produtos_img)
           image.load(produto.imgUrl)
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_catalogo_produtos_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size


    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}
