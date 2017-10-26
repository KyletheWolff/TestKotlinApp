package wolffincdevelopment.com.testkotlinapp.service.model

/**
 * Created by Kyle Wolff on 10/19/17.
 */
data class Forecast(
        val cod: Int,
        val message: Double,
        val cnt: Int,
        val list: List<Dates>,
        val city: City
)

data class Dates(
        val dt: Long,
        val main: Main,
        val weather: List<Weather>,
        val clouds: Clouds,
        val wind: Wind,
        val sys: System,
        val dt_txt: String
)

data class Wind(
        val speed: Double,
        val deg: Double
)

data class City(
        val name: String,
        val coord: Coordinates,
        val country: String
)

data class Main(
        val temp: Double,
        val temp_min: Double,
        val temp_max: Double,
        val pressure: Double,
        val sea_level: Double,
        val grnd_level: Double,
        val humidity: Int,
        val temp_kf: Double
)

data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
)

data class Coordinates(
        val lat: Double,
        val lon: Double
)

data class Clouds(
        val all: Int
)

data class System(
        val pod: String
)

