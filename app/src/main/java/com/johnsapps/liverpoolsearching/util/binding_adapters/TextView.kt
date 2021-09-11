package com.johnsapps.liverpoolsearching.util.binding_adapters
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.johnsapps.liverpoolsearching.util.extension.toPrice



@BindingAdapter("priceText")
fun TextView.setPriceText(price:Double){
    this.text = price.toPrice()
}

@BindingAdapter("priceStrikeText")
fun TextView.setPriceStrikeText(value:Boolean){
    if(value){
        val content = this.text
        val spannableString1 = SpannableString(content)
        spannableString1.setSpan(StrikethroughSpan(),0,content.length,0)
        this.text = spannableString1
    }
}