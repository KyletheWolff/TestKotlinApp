package wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel

import android.databinding.ObservableField
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Kyle Wolff on 10/23/17.
 */
class SearchViewModel(private val callback: Callback) : View.OnKeyListener, TextView.OnEditorActionListener {

    var searchButtonEnabled = ObservableField<Boolean>(false)
    var text: String = ""

    interface Callback {
        fun onStartWeatherInfoActivity(zip: String)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

        v as EditText
        text = v.text.toString()

        searchButtonEnabled.set(v.text.length == 5)

        return false
    }

    fun onClick() {
        callback.onStartWeatherInfoActivity(text)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE && text.length == 5) {
            onClick()
            return true
        }

        return false
    }
}