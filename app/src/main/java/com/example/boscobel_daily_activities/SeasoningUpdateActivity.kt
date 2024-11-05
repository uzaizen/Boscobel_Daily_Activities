package com.example.boscobel_daily_activities


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
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
import com.example.boscobel_daily_activities.databinding.ActivitySeasoningUpdateBinding

//import kotlinx.android.synthetic.main.activity_num_update.*
//import kotlinx.android.synthetic.main.activity_seasoning_update.*

class SeasoningUpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySeasoningUpdateBinding
    lateinit var updatename: String
    var updateneedcook: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_seasoning_update)

        // ViewBindingの初期化
        binding = ActivitySeasoningUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val UpdateName = intent.getStringExtra("Name")
        val UpdateNeedCook = intent.getStringExtra("NeedCook")

        updatename = UpdateName.toString()
        binding.SeasoningName.text = updatename

        updateneedcook = UpdateNeedCook.toString().toInt()

        if (updateneedcook == 0){  binding.toggleButton.isChecked = false}
        else if (updateneedcook ==1) {binding.toggleButton.isChecked = true}

        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                updateneedcook = 1
                // The toggle is enabled
            } else {
                updateneedcook = 0
                // The toggle is disabled
            }
        }


        binding.SeasoningUpdateButton.setOnClickListener { view ->

            /*キーボードを消す*/
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            when (updatename){
                Mayonnaise.Name ->   {
                    Mayonnaise.Needcook = updateneedcook
                }

                Tartar.Name -> {
                    Tartar.Needcook = updateneedcook
                }

                Mayodre.Name -> {
                    Mayodre.Needcook = updateneedcook
                }

                Soydre.Name -> {
                    Soydre.Needcook = updateneedcook
                }

                Rapeedre.Name -> {
                    Rapeedre.Needcook = updateneedcook
                }

                TomatoSauce.Name -> {
                    TomatoSauce.Needcook = updateneedcook
                }

                Balsamic.Name -> {
                    Balsamic.Needcook = updateneedcook
                }

                SmokeShoyu.Name -> {
                    SmokeShoyu.Needcook = updateneedcook
                }

                Whey.Name -> {
                    Whey.Needcook = updateneedcook
                }

                PizzaSauce.Name -> {
                    PizzaSauce.Needcook = updateneedcook
                }

                Raisin.Name -> {
                    Raisin.Needcook = updateneedcook
                }

                Blueberry.Name -> {
                    Blueberry.Needcook = updateneedcook
                }

                RR4.Name -> {
                    RR4.Needcook = updateneedcook
                }

                RR5.Name -> {
                    RR5.Needcook = updateneedcook
                }

                RR6.Name -> {
                    RR6.Needcook = updateneedcook
                }

                RR7.Name -> {
                    RR7.Needcook = updateneedcook
                }

                RR8.Name -> {
                    RR8.Needcook = updateneedcook
                }

                RR9.Name -> {
                    RR9.Needcook = updateneedcook
                }

            }
            finish()
        }
    }
}