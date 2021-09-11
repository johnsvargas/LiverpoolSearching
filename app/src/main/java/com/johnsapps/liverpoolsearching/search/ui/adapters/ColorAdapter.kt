package com.johnsapps.liverpoolsearching.search.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnsapps.liverpoolsearching.databinding.ItemColorBinding
import com.johnsapps.liverpoolsearching.search.data.model.VariantsColor
import android.graphics.PorterDuff




class ColorAdapter: RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private val list :ArrayList<VariantsColor> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemColorBinding.inflate(layoutInflater, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(list[position].colorHex)
    }

    override fun getItemCount()= list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : MutableList<VariantsColor>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ColorViewHolder(private val binding:ItemColorBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(color:String){
            binding.ivItemColor.clearColorFilter()
            val myColor: Int = Color.parseColor(color)
            binding.ivItemColor.setColorFilter(myColor, PorterDuff.Mode.MULTIPLY)

        }
    }


}