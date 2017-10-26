package wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel

import android.databinding.ObservableField
import wolffincdevelopment.com.testkotlinapp.service.model.Dates
import wolffincdevelopment.com.testkotlinapp.util.DateUtils

/**
 * Created by Kyle Wolff on 10/19/17.
 */
class ItemDetailViewModel(date: Dates, isImperial: Boolean) {

    private var symbol: String = if (isImperial) "\u2109" else "\u2103"

    val time: ObservableField<String> = ObservableField(
            "Time: " + DateUtils.hourMin.format(DateUtils.toFullDate.parse(date.dt_txt)))

    val mainTemp: ObservableField<String> = ObservableField(
            "Temp: " + date.main.temp.toString() + " $symbol")

    val imageIcon: ObservableField<String> = ObservableField(date.weather[0].icon)

    val desc: ObservableField<String> = ObservableField(date.weather[0].description.capitalize())

}