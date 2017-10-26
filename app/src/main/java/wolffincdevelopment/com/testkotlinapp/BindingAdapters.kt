package wolffincdevelopment.com.testkotlinapp

import android.content.Context
import android.content.res.Resources
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Kyle Wolff on 10/20/17.
 */
object BindingAdapters {

        // Specifies that a static method or field needs to be generated from this element
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url:String) {

            val c: Context = view.context
            val r: Resources = c.resources

            val fullUrl = r.getString(R.string.base_url_for_weather_icons) + url + r.getString(R.string.weather_file_type)

            Picasso.with(c).load(fullUrl).into(view)
        }
}