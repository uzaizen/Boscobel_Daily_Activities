package com.example.boscobel_daily_activities

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter2(context: Context?, var mSeasoning1List: List<Seasoning1>) : ArrayAdapter<Seasoning1>(context!!, 0, mSeasoning1List) {

    private val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // dish1の取得
        val seasoning1 = mSeasoning1List[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.row2, parent, false)
        }

        // 各Viewの設定
        val seasoningName = view?.findViewById<TextView>(R.id.rwSeasoningName)
        seasoningName?.text = seasoning1.Name

        val needcook = view?.findViewById<TextView>(R.id.rwNeedCook)
        needcook?.text = "%,1d".format(seasoning1.Needcook)

        return view!!
    }
}