package com.johnsapps.liverpoolsearching.search.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnsapps.liverpoolsearching.databinding.ItemProductBinding
import com.johnsapps.liverpoolsearching.search.data.model.Product


class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val listProduct :MutableList<Product> = ArrayList()

    private var onClick: ((product: Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }

    override fun getItemCount() = listProduct.size

    fun setData(data : MutableList<Product>) {
        val size = listProduct.size
        listProduct.addAll(data)
        val sizeNew = listProduct.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun clearListProducts(){
        val size = listProduct.size
        listProduct.clear()
        notifyItemRangeRemoved(0,size)
    }

    inner class ProductViewHolder(private val binding:ItemProductBinding )
        :RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product){

            with(binding){
                this.product = product
                root.setOnClickListener {
                    onClick?.invoke(product)
                }
                product.variantsColor?.let{
                    if(it.size > 1){
                        val adapter = ColorAdapter()
                        adapter.setData(it)
                        binding.rvItemProductListColor.adapter = adapter
                    }
                }
            }

        }

    }
}
