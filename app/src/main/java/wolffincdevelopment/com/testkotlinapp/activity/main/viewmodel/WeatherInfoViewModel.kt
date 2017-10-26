package wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import io.reactivex.functions.Consumer
import wolffincdevelopment.com.testkotlinapp.R
import wolffincdevelopment.com.testkotlinapp.service.call.ForecastCall
import wolffincdevelopment.com.testkotlinapp.service.model.Forecast
import java.util.*

/**
 * Created by Kyle Wolff on 10/19/17.
 */
class WeatherInfoViewModel(private val c: Context, private val callback: Callback, private val zip: String) {

    enum class Unit {
        IMPERIAL,
        METRIC
    }

    var title: ObservableField<String> = ObservableField("")
    var showError: ObservableField<Boolean> = ObservableField(false)
    var errorText: ObservableField<String> = ObservableField("")
    var showProgessWheel: ObservableField<Boolean> = ObservableField(true)

    var isImperial: Boolean = true
    var unit: Unit = Unit.IMPERIAL

    interface Callback {
        fun onDataReady(forecast: Forecast)
        fun onShowProgressView(show: Boolean)
    }

    init {
        updateData(Unit.IMPERIAL)
    }

    fun updateData(unit: Unit) {

        isImperial = unit == Unit.IMPERIAL

        showProgessWheel.set(true)
        callback.onShowProgressView(showProgessWheel.get())

        ForecastCall(c, zip, unit.name).execute(
                Consumer { forecast -> onSuccess(forecast) },
                Consumer { error ->
                    onError(error)
                    Log.e("ERROR", "ForecastCall Failed")
                })
    }

    private fun onSuccess(forecast: Forecast) {
        title.set(String.format(Locale.getDefault(), c.resources.getString(R.string.weather_for), forecast.city.name))
        callback.onDataReady(forecast)
        showError.set(false)
        showProgessWheel.set(false)

        callback.onShowProgressView(showProgessWheel.get())
    }

    private fun onError(t: Throwable) {
        showError.set(true)
        errorText.set(t.message.orEmpty())
        showProgessWheel.set(false)

        callback.onShowProgressView(showProgessWheel.get())
    }
}