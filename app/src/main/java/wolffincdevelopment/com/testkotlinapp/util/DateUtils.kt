package wolffincdevelopment.com.testkotlinapp.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Kyle Wolff on 10/19/17.
 */
class DateUtils {

    companion object {
        val monthDayYear = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val hourMin = SimpleDateFormat("h:mm", Locale.getDefault())
        val toFullDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dayOfWeekMonth = SimpleDateFormat("EEE, d, MMM", Locale.getDefault())
    }
}