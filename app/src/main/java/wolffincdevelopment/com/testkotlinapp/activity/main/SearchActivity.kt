package wolffincdevelopment.com.testkotlinapp.activity.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import wolffincdevelopment.com.testkotlinapp.R
import wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel.SearchViewModel
import wolffincdevelopment.com.testkotlinapp.databinding.SearchActivityBinding


/**
 * Created by Kyle Wolff on 10/23/17.
 */

class SearchActivity : AppCompatActivity(), SearchViewModel.Callback {

    companion object {
        val ZIP_KEY = "ZIP"
    }

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: SearchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = SearchViewModel(this)
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity)

        binding.item = searchViewModel
        binding.searchText.setOnKeyListener(searchViewModel)
        binding.searchText.setOnEditorActionListener(searchViewModel)
    }

    override fun onStartWeatherInfoActivity(zip: String) {
        val i = Intent(this, WeatherInfoActivity::class.java)
        i.putExtra(ZIP_KEY, zip)
        startActivity(i)
    }
}
