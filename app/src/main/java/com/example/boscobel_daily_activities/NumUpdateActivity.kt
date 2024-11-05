package com.example.boscobel_daily_activities

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.boscobel_daily_activities.MainApplication.Companion.Bread
import com.example.boscobel_daily_activities.MainApplication.Companion.CarrotRapee
import com.example.boscobel_daily_activities.MainApplication.Companion.Chiken
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryEgg
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryRice
import com.example.boscobel_daily_activities.MainApplication.Companion.Gratin
import com.example.boscobel_daily_activities.MainApplication.Companion.Hamberg
import com.example.boscobel_daily_activities.MainApplication.Companion.Locomoko
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
import com.example.boscobel_daily_activities.databinding.ActivityNumUpdateBinding

//import kotlinx.android.synthetic.main.activity_num_update.*


class NumUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNumUpdateBinding
    lateinit var updatename: String
    var updaterequired: Int = 0
    var updatestock: Int = 0
    var updatecook: Int = 0
    var updatetotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_num_update)

        // ViewBindingの初期化
        binding = ActivityNumUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* EditTextを入力できないようにする */
        binding.EditRequired.setFocusable(false)
        binding.EditTotal.setFocusable(false)

        val UpdateName = intent.getStringExtra("Name")
        val UpdateRequired = intent.getStringExtra("Required")
        val UpdateStock = intent.getStringExtra("Stock")
        val UpdateCook = intent.getStringExtra("Cook")
        val UpdateTotal = intent.getStringExtra("Total")

        updatename = UpdateName.toString()
        updaterequired = UpdateRequired.toString().toInt()
        updatestock = UpdateStock.toString().toInt()
        updatecook = UpdateCook.toString().toInt()
        updatetotal = UpdateTotal.toString().toInt()

        when (UpdateName) {
            Soup.Name -> {
                updatestock = Soup.Stock
                updatecook = Soup.Cook
                updatetotal = Soup.Total
            }

            PotatoSalad.Name -> {
                updatestock = PotatoSalad.Stock
                updatecook = PotatoSalad.Cook
                updatetotal = PotatoSalad.Total
            }

            CarrotRapee.Name -> {
                updatestock = CarrotRapee.Stock
                updatecook = CarrotRapee.Cook
                updatetotal = CarrotRapee.Total
            }

            Rice.Name -> {
                updatestock = Rice.Stock
                updatecook = Rice.Cook
                updatetotal = Rice.Total
            }

            CurryRice.Name -> {
                updatestock = CurryRice.Stock
                updatecook = CurryRice.Cook
                updatetotal = CurryRice.Total
            }

            CurryEgg.Name -> {
                updatestock = CurryEgg.Stock
                updatecook = CurryEgg.Cook
                updatetotal = CurryEgg.Total
            }

            Hamberg.Name -> {
                updatestock = Hamberg.Stock
                updatecook = Hamberg.Cook
                updatetotal = Hamberg.Total
            }

            Locomoko.Name -> {
                updatestock = Locomoko.Stock
                updatecook = Locomoko.Cook
                updatetotal = Locomoko.Total
            }

            Sarmon.Name -> {
                updatestock = Sarmon.Stock
                updatecook = Sarmon.Cook
                updatetotal = Sarmon.Total
            }

            Chiken.Name -> {
                updatestock = Chiken.Stock
                updatecook = Chiken.Cook
                updatetotal = Chiken.Total
            }

            RoastBeef.Name -> {
                updatestock = RoastBeef.Stock
                updatecook = RoastBeef.Cook
                updatetotal = RoastBeef.Total
            }

            Pizza.Name -> {
                updatestock = Pizza.Stock
                updatecook = Pizza.Cook
                updatetotal = Pizza.Total
            }

            VenisonCurry.Name -> {
                updatestock = VenisonCurry.Stock
                updatecook = VenisonCurry.Cook
                updatetotal = VenisonCurry.Total
            }

            VenisonSteak.Name -> {
                updatestock = VenisonSteak.Stock
                updatecook = VenisonSteak.Cook
                updatetotal = VenisonSteak.Total
            }

            Gratin.Name -> {
                updatestock = Gratin.Stock
                updatecook = Gratin.Cook
                updatetotal = Gratin.Total
            }

            WhiteSource.Name -> {
                updatestock = WhiteSource.Stock
                updatecook = WhiteSource.Cook
                updatetotal = WhiteSource.Total
            }

            Bread.Name -> {
                updatestock = Bread.Stock
                updatecook = Bread.Cook
                updatetotal = Bread.Total
            }

            R8.Name -> {
                updatestock = R8.Stock
                updatecook = R8.Cook
                updatetotal = R8.Total
            }

            R9.Name -> {
                updatestock = R9.Stock
                updatecook = R9.Cook
                updatetotal = R9.Total
            }

            R0.Name -> {
                updatestock = R0.Stock
                updatecook = R0.Cook
                updatetotal = R0.Total
            }

            R11.Name -> {
                updatestock = R11.Stock
                updatecook = R11.Cook
                updatetotal = R11.Total
            }

            R12.Name -> {
                updatestock = R12.Stock
                updatecook = R12.Cook
                updatetotal = R12.Total
            }


        }

        recul()

        var stockswitch: Int = 0   //最初の処理＝０　2回目以降の処理＝１
        var stockswitch2: Int = 0  //変更する項目の頭（これ以上戻れない位置）にカーソルが来たら１

        binding.EditStock.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (stockswitch2 == 1) {
                    stockswitch2 = 0
                    return
                }
                updatestock = p0.toString().toInt()
                if (stockswitch == 0) {
                    updatecook = updaterequired - updatestock
                    if (updatecook < 0) {updatecook = 0}
                    updatetotal = updatestock + updatecook
                    recul()
                } else
                    if (stockswitch == 1) {
                        updatetotal = updatestock + updatecook
                        recul()
                    }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p1 == 0 && p2 == 1 && p3 == 0) {
                    stockswitch2 = 1
                    return
                }
            }
        })

        var cookswitch2: Int = 0
        binding.EditCook.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (cookswitch2 == 1) {
                    cookswitch2 = 0
                    return
                }
                stockswitch = 1
                updatecook = p0.toString().toInt()
                if (updatecook < 10000) {
                    updatetotal = updatestock + updatecook
                    recul()
                } else {}
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p1 == 0 && p2 == 1 && p3 == 0) {
                    cookswitch2 = 1
                    return
                }
            }
        })

        binding.FixButton.setOnClickListener { view ->

            /*キーボードを消す*/
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            when (UpdateName) {
                Soup.Name -> {
                    Soup.Stock = updatestock
                    Soup.Cook = updatecook
                    Soup.Total = updatetotal
                }

                PotatoSalad.Name -> {
                    PotatoSalad.Stock = updatestock
                    PotatoSalad.Cook = updatecook
                    PotatoSalad.Total = updatetotal
                }

                CarrotRapee.Name -> {
                    CarrotRapee.Stock = updatestock
                    CarrotRapee.Cook = updatecook
                    CarrotRapee.Total = updatetotal
                }

                Rice.Name -> {
                    Rice.Stock = updatestock
                    Rice.Cook = updatecook
                    Rice.Total = updatetotal
                }

                CurryRice.Name -> {
                    CurryRice.Stock = updatestock
                    CurryRice.Cook = updatecook
                    CurryRice.Total = updatetotal
                }

                CurryEgg.Name -> {
                    CurryEgg.Stock = updatestock
                    CurryEgg.Cook = updatecook
                    CurryEgg.Total = updatetotal
                }

                Hamberg.Name -> {
                    Hamberg.Stock = updatestock
                    Hamberg.Cook = updatecook
                    Hamberg.Total = updatetotal
                }

                Locomoko.Name -> {
                    Locomoko.Stock = updatestock
                    Locomoko.Cook = updatecook
                    Locomoko.Total = updatetotal
                }

                Sarmon.Name -> {
                    Sarmon.Stock = updatestock
                    Sarmon.Cook = updatecook
                    Sarmon.Total = updatetotal
                }

                Chiken.Name -> {
                    Chiken.Stock = updatestock
                    Chiken.Cook = updatecook
                    Chiken.Total = updatetotal
                }

                RoastBeef.Name -> {
                    RoastBeef.Stock = updatestock
                    RoastBeef.Cook = updatecook
                    RoastBeef.Total = updatetotal
                }

                Pizza.Name -> {
                    Pizza.Stock = updatestock
                    Pizza.Cook = updatecook
                    Pizza.Total = updatetotal
                }

                VenisonCurry.Name -> {
                    VenisonCurry.Stock = updatestock
                    VenisonCurry.Cook = updatecook
                    VenisonCurry.Total = updatetotal
                }

                VenisonSteak.Name -> {
                    VenisonSteak.Stock = updatestock
                    VenisonSteak.Cook = updatecook
                    VenisonSteak.Total = updatetotal
                }

                Gratin.Name -> {
                    Gratin.Stock = updatestock
                    Gratin.Cook = updatecook
                    Gratin.Total = updatetotal
                }

                WhiteSource.Name -> {
                    WhiteSource.Stock = updatestock
                    WhiteSource.Cook = updatecook
                    WhiteSource.Total = updatetotal
                }

                Bread.Name -> {
                    Bread.Stock = updatestock
                    Bread.Cook = updatecook
                    Bread.Total = updatetotal
                }

                R8.Name -> {
                    R8.Stock = updatestock
                    R8.Cook = updatecook
                    R8.Total = updatetotal
                }

                R9.Name -> {
                    R9.Stock = updatestock
                    R9.Cook = updatecook
                    R9.Total = updatetotal
                }

                R0.Name -> {
                    R0.Stock = updatestock
                    R0.Cook = updatecook
                    R0.Total = updatetotal
                }

                R11.Name -> {
                    R11.Stock = updatestock
                    R11.Cook = updatecook
                    R11.Total = updatetotal
                }

                R12.Name -> {
                    R12.Stock = updatestock
                    R12.Cook = updatecook
                    R12.Total = updatetotal
                }


            }
            finish()
        }
    }

    fun recul() {
        binding.UpdateTitle.text = updatename
        binding.EditRequired.hint = updaterequired.toString()
        binding.EditStock.hint = updatestock.toString()
        binding.EditCook.hint = updatecook.toString()
        binding.EditTotal.hint = updatetotal.toString()


        /*　EditTextのTextにStringをセットする方法
        UpdateTitle.setText(updatename, TextView.BufferType.NORMAL)
        EditRequired.setText(updaterequired.toString(), TextView.BufferType.NORMAL)
        EditStock.setText(updatestock.toString(), TextView.BufferType.NORMAL)
        EditCook.setText(updatecook.toString(), TextView.BufferType.NORMAL)
        EditTotal.setText(updatetotal.toString(), TextView.BufferType.NORMAL)

         */
    }
}
