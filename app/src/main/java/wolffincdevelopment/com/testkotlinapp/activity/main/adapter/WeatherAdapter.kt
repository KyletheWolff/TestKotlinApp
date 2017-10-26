package wolffincdevelopment.com.testkotlinapp.activity.main.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import wolffincdevelopment.com.testkotlinapp.R
import wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel.ItemDetailViewModel
import wolffincdevelopment.com.testkotlinapp.activity.main.viewmodel.ItemViewModel
import wolffincdevelopment.com.testkotlinapp.databinding.WeatherDetailItemBinding
import wolffincdevelopment.com.testkotlinapp.databinding.WeatherItemBinding
import wolffincdevelopment.com.testkotlinapp.service.model.Dates
import wolffincdevelopment.com.testkotlinapp.util.DateUtils
import java.util.*

/**
 * Created by Kyle Wolff on 10/19/17.
 */
class WeatherAdapter : BaseExpandableListAdapter() {

    private var titleList: ArrayList<String> = ArrayList()
    private var detailList: HashMap<String, List<Dates>> = hashMapOf()

    private var isImperial: Boolean = true

    fun updateData(dates: ArrayList<Dates>, isImperial: Boolean) {
        this.isImperial = isImperial

        titleList.clear()
        detailList.clear()

        var previousTitle = ""

        for (d in dates) {

            val date: String = DateUtils.monthDayYear.format(DateUtils.monthDayYear.parse(d.dt_txt))

            if (!previousTitle.contentEquals(date)) {
                titleList.add(date)
            }

            previousTitle = date
        }

        detailList = hashMapOf()

        var previousKey = ""

        for (i in 0 until titleList.size) {

            val listOfDates: ArrayList<Dates> = ArrayList()

            dates.removeAll(detailList[previousKey].orEmpty())

            val key: String = titleList[i]

            for (d in dates) {

                val date =  DateUtils.monthDayYear.format(DateUtils.monthDayYear.parse(d.dt_txt))

                if (key.contentEquals(date)) {
                    listOfDates.add(d)
                } else {
                    detailList.put(key, listOfDates)
                    previousKey = key

                    continue
                }

                if (i == titleList.size - 1) {
                    detailList.put(key, listOfDates)
                }
            }
        }
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        var count = 1

        if (detailList.containsKey(titleList[groupPosition])) {
            count = detailList[(titleList[groupPosition])]!!.size
        }

        return count
    }

    override fun getGroup(groupPosition: Int): Any {
        return titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return detailList[titleList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        val binding: WeatherDetailItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.weather_detail_item, parent, false)
        binding.item = ItemDetailViewModel(detailList[titleList[groupPosition]]!![childPosition], isImperial)

        return binding.root
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        val binding: WeatherItemBinding =
                if (convertView == null) {
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent!!.context),
                            R.layout.weather_item, parent,
                            false)
                } else {
                    DataBindingUtil.getBinding(convertView)
                }

        binding.item = ItemViewModel(titleList[groupPosition], titleList[groupPosition] == titleList.last())

        return binding.root
    }

    override fun getGroupCount(): Int {
        return titleList.size
    }
}