package com.example.boscobel_daily_activities


import android.content.Context
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class CustomAdapter(context: Context?, var mDish1List: List<Dish1>) : ArrayAdapter<Dish1>(context!!, 0, mDish1List) {

    private val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // dish1の取得
        val dish1 = mDish1List[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.row, parent, false)
        }

        // 各Viewの設定
        val menuName = view?.findViewById<TextView>(R.id.rwMenuName)
        menuName?.text = dish1.Name

        val required = view?.findViewById<TextView>(R.id.rwRequired)
        if (dish1.Required > 999) {required?.text = "%,12d".format(dish1.Required)}
        else if (dish1.Required > 99) {required?.text = " "+"%,12d".format(dish1.Required)}
        else if (dish1.Required > 9) {required?.text = "  "+"%,12d".format(dish1.Required)}
        else {required?.text = "   "+"%,12d".format(dish1.Required)}


        val stock = view?.findViewById<TextView>(R.id.rwStock)
        if (dish1.Stock > 999) {stock?.text = "%,12d".format(dish1.Stock)}
        else if (dish1.Stock > 99) {stock?.text = " "+"%,12d".format(dish1.Stock)}
        else if (dish1.Stock > 9) {stock?.text = "  "+"%,12d".format(dish1.Stock)}
        else {stock?.text = "   "+"%,12d".format(dish1.Stock)}


        val cook = view?.findViewById<TextView>(R.id.rwToday)
        if (dish1.Cook > 999) {cook?.text = "%,12d".format(dish1.Cook)}
        else if (dish1.Cook > 99) {cook?.text = " "+"%,12d".format(dish1.Cook)}
        else if (dish1.Cook > 9) {cook?.text = "  "+"%,12d".format(dish1.Cook)}
        else {cook?.text = "   "+"%,12d".format(dish1.Cook)}

        val total = view?.findViewById<TextView>(R.id.rwTotal)
        total?.text = "%,12d".format(dish1.Total)
        if (dish1.Total > 999) {total?.text = "%,12d".format(dish1.Total)}
        else if (dish1.Total > 99) {total?.text = " "+"%,12d".format(dish1.Total)}
        else if (dish1.Total > 9) {total?.text = "  "+"%,12d".format(dish1.Total)}
        else {total?.text = "   "+"%,12d".format(dish1.Total)}

        return view!!
    }
}