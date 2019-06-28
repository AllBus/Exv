package com.kos.exv.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.kos.exv.models.Age
import com.kos.exv.models.Worker
import com.kos.exv.models.ext.SpecialitiesList
import java.text.SimpleDateFormat
import java.util.*


object WorkerUtil {

    @SuppressLint("ConstantLocale")
    val formatDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    init {
        formatDate.timeZone = Age.timeZone
    }


    @JvmStatic
    @BindingAdapter("formatDate", "noDate")
    fun formatBirthday(view: TextView, age: Age?, noDateText: String) {

        view.text =
            if (age?.defined == true) {
                val calendar = Calendar.getInstance()
                calendar.timeZone = Age.timeZone
                calendar.timeInMillis = age.birthday

                formatDate.format(calendar.time)
            } else {
                noDateText
            }
    }

    @JvmStatic
    @BindingAdapter("ageText", "quantityRes", "noDate")
    fun ageText(view: TextView, age: Age?, @PluralsRes quantityRes: Int, noDateText: String) {

        view.text =
            if (age?.defined == true) {
                val ageValue = age.value()
                view.context.resources.getQuantityString(quantityRes, ageValue, ageValue)
            } else {
                noDateText
            }
    }


    @JvmStatic
    @BindingConversion
    fun convertSpecialitiesToString(specialities: SpecialitiesList?): String {
        return specialities?.specialities?.joinToString("\n") { it.name } ?: ""

    }

    @JvmStatic
    fun formatName(name: String?): String {
        return name?.toLowerCase()?.capitalize() ?: ""
    }

    @JvmStatic
    fun fullName(worker: Worker?): String {
        return worker?.let {
            listOf(worker.firstName, worker.lastName).filter { it.isNotEmpty() }
                .joinToString(" ") { formatName(it) }
        } ?: ""
    }

}