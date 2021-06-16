package site.yoonsang.covidnow.util

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import site.yoonsang.covidnow.R

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

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setDistance")
    fun setDistance(textView: TextView, distance: String?) {
        if (distance == "") {
            textView.text = "거리 정보 없음"
            textView.setTextColor(textView.context.getColor(R.color.dark_gray))
        } else {
            textView.text = "${distance}m"
            textView.setTextColor(textView.context.getColor(R.color.black))
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setPhone")
    fun setPhone(textView: TextView, phone: String?) {
        if (phone == "") {
            textView.text = "전화번호 정보 없음"
            textView.setTextColor(textView.context.getColor(R.color.black))
        } else {
            textView.text = phone
            textView.setTextColor(textView.context.getColor(R.color.select_color))
        }
    }
}