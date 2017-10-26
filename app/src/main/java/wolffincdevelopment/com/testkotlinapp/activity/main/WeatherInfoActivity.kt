package wolffincdevelopment.com.testkotlinapp.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import wolffincdevelopment.com.testkotlinapp.R
import wolffincdevelopment.com.testkotlinapp.activity.main.adapter.WeatherAdapter
import wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel.WeatherInfoViewModel
import wolffincdevelopment.com.testkotlinapp.databinding.ActivityMainBinding
import wolffincdevelopment.com.testkotlinapp.service.model.Dates
import wolffincdevelopment.com.testkotlinapp.service.model.Forecast
import java.util.*


class WeatherInfoActivity : AppCompatActivity(), WeatherInfoViewModel.Callback {

    private lateinit var w: WeatherAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherInfoViewModel
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val zip: String = intent.getStringExtra(SearchActivity.ZIP_KEY)

        viewModel = WeatherInfoViewModel(this, this, zip)
        binding.item = viewModel

        w = WeatherAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        this.menu = menu!!
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.celsius ->
                if (!item.isChecked) {

                    item.isChecked = true
                    menu.findItem(R.id.fahrenheit).isChecked = false
                    viewModel.updateData(WeatherInfoViewModel.Unit.METRIC)
                }
            R.id.fahrenheit ->
                if (!item.isChecked) {
                    item.isChecked = true
                    menu.findItem(R.id.celsius).isChecked = false
                    viewModel.updateData(WeatherInfoViewModel.Unit.IMPERIAL)
                }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onShowProgressView(show: Boolean) {
        if (show) {
            binding.progressView.animate()
        } else {
            binding.progressView.clearAnimation()
        }
    }

    override fun onDataReady(forecast: Forecast) {

        val arrayList: ArrayList<Dates> = ArrayList()
        arrayList.addAll(forecast.list)
        w.updateData(arrayList, viewModel.isImperial)

        binding.listView.setAdapter(w)
        w.notifyDataSetChanged()
    }
}
