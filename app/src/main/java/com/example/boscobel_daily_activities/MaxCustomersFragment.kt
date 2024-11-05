package com.example.boscobel_daily_activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.accessibility.AccessibilityViewCommand
import com.example.boscobel_daily_activities.MainApplication.Companion.Bread
import com.example.boscobel_daily_activities.MainApplication.Companion.CarrotRapee
import com.example.boscobel_daily_activities.MainApplication.Companion.Chiken
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryEgg
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryRice
import com.example.boscobel_daily_activities.MainApplication.Companion.Gratin
import com.example.boscobel_daily_activities.MainApplication.Companion.Hamberg
import com.example.boscobel_daily_activities.MainApplication.Companion.Locomoko
import com.example.boscobel_daily_activities.MainApplication.Companion.MAXCUSTOMER
import com.example.boscobel_daily_activities.MainApplication.Companion.Pizza
import com.example.boscobel_daily_activities.MainApplication.Companion.PotatoSalad
import com.example.boscobel_daily_activities.MainApplication.Companion.R0
import com.example.boscobel_daily_activities.MainApplication.Companion.R11
import com.example.boscobel_daily_activities.MainApplication.Companion.R12
import com.example.boscobel_daily_activities.MainApplication.Companion.R8
import com.example.boscobel_daily_activities.MainApplication.Companion.R9
import com.example.boscobel_daily_activities.MainApplication.Companion.Rice
import com.example.boscobel_daily_activities.MainApplication.Companion.RoastBeef
import com.example.boscobel_daily_activities.MainApplication.Companion.Sarmon
import com.example.boscobel_daily_activities.MainApplication.Companion.Soup
import com.example.boscobel_daily_activities.MainApplication.Companion.VenisonCurry
import com.example.boscobel_daily_activities.MainApplication.Companion.VenisonSteak
import com.example.boscobel_daily_activities.MainApplication.Companion.WhiteSource
import com.example.boscobel_daily_activities.databinding.FragmentMaxCustomersBinding

//import kotlinx.android.synthetic.main.fragment_max_customers.*
//import kotlinx.android.synthetic.main.fragment_max_customers.view.*
//import kotlin.math.roundToInt

class MaxCustomersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentMaxCustomersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMaxCustomersBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.maxcustomersbutton.setOnClickListener(maxcustomernumberset())

        return view
    }

    private inner class maxcustomernumberset : View.OnClickListener {
        override fun onClick(view: View) {
            if (binding.maxcustomers.text == null) {
                MAXCUSTOMER=0
            } else {
                MAXCUSTOMER = binding.maxcustomers.text.toString().toInt()
                CulcRequired(MAXCUSTOMER)
            }
            /*キーボードを消す*/
            val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            binding.maxcustomers.clearFocus()
        }
    }

    fun CulcRequired (MaxCustomer:Int){
        Soup.Required = MaxCustomer * 120
        PotatoSalad.Required = MaxCustomer * 40
        CarrotRapee.Required = MaxCustomer * 20
        Rice.Required = MaxCustomer / 3
        Hamberg.Required = MaxCustomer / 2
        Locomoko.Required = MaxCustomer / 6
        Sarmon.Required = MaxCustomer / 6
        Chiken.Required = MaxCustomer / 6
        RoastBeef.Required = MaxCustomer / 5
        Pizza.Required = MaxCustomer / 7
        VenisonCurry.Required = MaxCustomer / 7
        VenisonSteak.Required = MaxCustomer / 7
        CurryRice.Required = Math.ceil(VenisonCurry.Required.toDouble() / 2.0).toInt()
        CurryEgg.Required = Math.ceil(VenisonCurry.Required.toDouble() / 2.0).toInt()
        Gratin.Required = MaxCustomer / 4
        WhiteSource.Required = MaxCustomer / 6
        Bread.Required = 6
        R8.Required = 0
        R9.Required = 0
        R0.Required = 0
        R11.Required = 0
        R12.Required = 0
    }

    override fun onStart() {
        super.onStart()
        binding.maxcustomers.setText(MAXCUSTOMER.toString())
    }
}

