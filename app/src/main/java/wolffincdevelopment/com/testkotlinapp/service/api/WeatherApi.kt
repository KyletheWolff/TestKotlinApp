package wolffincdevelopment.com.testkotlinapp.service.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import wolffincdevelopment.com.testkotlinapp.service.model.Forecast

/**
 * Created by Kyle Wolff on 10/19/17.
 */
interface WeatherApi {

    @GET("/data/2.5/forecast")
    fun getForecast(
            @Query("zip") zip: String,
            @Query("mode") mode: String,
            @Query("units") unit: String): Observable<Forecast>
}