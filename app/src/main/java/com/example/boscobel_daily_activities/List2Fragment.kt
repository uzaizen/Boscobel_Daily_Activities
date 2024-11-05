package com.example.boscobel_daily_activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.boscobel_daily_activities.MainApplication.Companion.Balsamic
import com.example.boscobel_daily_activities.MainApplication.Companion.Blueberry
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayodre
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayonnaise
import com.example.boscobel_daily_activities.MainApplication.Companion.PizzaSauce
import com.example.boscobel_daily_activities.MainApplication.Companion.RR4
import com.example.boscobel_daily_activities.MainApplication.Companion.RR5
import com.example.boscobel_daily_activities.MainApplication.Companion.RR6
import com.example.boscobel_daily_activities.MainApplication.Companion.RR7
import com.example.boscobel_daily_activities.MainApplication.Companion.RR8
import com.example.boscobel_daily_activities.MainApplication.Companion.RR9
import com.example.boscobel_daily_activities.MainApplication.Companion.Raisin
import com.example.boscobel_daily_activities.MainApplication.Companion.Rapeedre
import com.example.boscobel_daily_activities.MainApplication.Companion.SmokeShoyu
import com.example.boscobel_daily_activities.MainApplication.Companion.Soydre
import com.example.boscobel_daily_activities.MainApplication.Companion.Tartar
import com.example.boscobel_daily_activities.MainApplication.Companion.TomatoSauce
import com.example.boscobel_daily_activities.MainApplication.Companion.Whey

//List2Fragment is to show seasoning list to right side of window to check to make or not

class List2Fragment : Fragment() {

    lateinit var mSeasoning1List: ArrayList<Seasoning1>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list2, container, false)

        mSeasoning1List = arrayListOf(Mayonnaise, Tartar, Mayodre, Soydre ,Rapeedre, TomatoSauce, Balsamic, SmokeShoyu, Whey, PizzaSauce, Raisin, Blueberry, RR4, RR5, RR6, RR7, RR8, RR9)

        val list2View = view.findViewById<ListView>(R.id.list2_view)

        val adapter = CustomAdapter2(requireContext(), mSeasoning1List)

        list2View.adapter = adapter

        list2View.setOnItemClickListener{ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position)
            val seasoning1 = mSeasoning1List[position]

            val UpdateName = seasoning1.Name
            val NeedCook = seasoning1.Needcook.toString()
            Log.d("uztest","Needcook = "+NeedCook)

            val intent2NumInput = Intent(
//                    requireContext(),
                getActivity(),
                SeasoningUpdateActivity::class.java
            )

            intent2NumInput.putExtra("Name", UpdateName)
            intent2NumInput.putExtra("NeedCook", NeedCook)

            startActivity(intent2NumInput)

        }


        return view
    }

    override fun onStart() {
        super.onStart()
        mSeasoning1List = arrayListOf(Mayonnaise, Tartar, Mayodre, Soydre, Rapeedre, TomatoSauce, Balsamic, SmokeShoyu, Whey, PizzaSauce, Raisin, Blueberry, RR4, RR5, RR6, RR7, RR8, RR9)

        val list2View = view?.findViewById<ListView>(R.id.list2_view)

        val adapter = CustomAdapter2(requireContext(), mSeasoning1List)

        list2View?.adapter = adapter

    }



}