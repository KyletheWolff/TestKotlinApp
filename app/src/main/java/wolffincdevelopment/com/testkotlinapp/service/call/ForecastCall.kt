package wolffincdevelopment.com.testkotlinapp.service.call

import android.content.Context
import io.reactivex.Observable
import wolffincdevelopment.com.testkotlinapp.service.Factory
import wolffincdevelopment.com.testkotlinapp.service.model.Forecast

/**
 * Created by Kyle Wolff on 10/19/17.
 */
class ForecastCall(private val c: Context, private val zip: String, private val unit: String) : AbstractCall<Forecast>() {

    override fun getObservable(): Observable<Forecast> {
        return Factory.getWeatherApi(c).getForecast(zip, "json", unit)
    }
}