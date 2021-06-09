package site.yoonsang.covidnow.util

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setCaseBefore")
    fun setCaseBefore(textView: TextView, totalBeforeCase: String?) {
        if (totalBeforeCase?.contains("-") == true) {
            textView.text = "(${totalBeforeCase}명)"
        } else {
            textView.text = "(+${totalBeforeCase}명)"
        }
    }
}