package com.example.boscobel_daily_activities

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Instrumentation
import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.provider.ContactsContract.ProfileSyncState.set
import android.provider.ContactsContract.SyncState.set
import android.provider.SyncStateContract.Helpers.set
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
//import androidx.lifecycle.ViewTreeLifecycleOwner.set
//import androidx.lifecycle.ViewTreeViewModelStoreOwner.set
//import androidx.savedstate.ViewTreeSavedStateRegistryOwner.set
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import com.example.boscobel_daily_activities.MainApplication.Companion.Balsamic
import com.example.boscobel_daily_activities.MainApplication.Companion.Blueberry
import com.example.boscobel_daily_activities.MainApplication.Companion.Bread
import com.example.boscobel_daily_activities.MainApplication.Companion.CarrotRapee
import com.example.boscobel_daily_activities.MainApplication.Companion.Chiken
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryEgg
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryRice
import com.example.boscobel_daily_activities.MainApplication.Companion.Gratin
import com.example.boscobel_daily_activities.MainApplication.Companion.Hamberg
import com.example.boscobel_daily_activities.MainApplication.Companion.Locomoko
import com.example.boscobel_daily_activities.MainApplication.Companion.MAXCUSTOMER
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayodre
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayonnaise
import com.example.boscobel_daily_activities.MainApplication.Companion.Pizza
import com.example.boscobel_daily_activities.MainApplication.Companion.PizzaSauce
import com.example.boscobel_daily_activities.MainApplication.Companion.PotatoSalad
import com.example.boscobel_daily_activities.MainApplication.Companion.R0
import com.example.boscobel_daily_activities.MainApplication.Companion.R11
import com.example.boscobel_daily_activities.MainApplication.Companion.R12
import com.example.boscobel_daily_activities.MainApplication.Companion.R8
import com.example.boscobel_daily_activities.MainApplication.Companion.R9
import com.example.boscobel_daily_activities.MainApplication.Companion.RR4
import com.example.boscobel_daily_activities.MainApplication.Companion.RR5
import com.example.boscobel_daily_activities.MainApplication.Companion.RR6
import com.example.boscobel_daily_activities.MainApplication.Companion.RR7
import com.example.boscobel_daily_activities.MainApplication.Companion.RR8
import com.example.boscobel_daily_activities.MainApplication.Companion.RR9
import com.example.boscobel_daily_activities.MainApplication.Companion.Raisin
import com.example.boscobel_daily_activities.MainApplication.Companion.Rapeedre
import com.example.boscobel_daily_activities.MainApplication.Companion.Rice
import com.example.boscobel_daily_activities.MainApplication.Companion.RoastBeef
import com.example.boscobel_daily_activities.MainApplication.Companion.Sarmon
import com.example.boscobel_daily_activities.MainApplication.Companion.SmokeShoyu
import com.example.boscobel_daily_activities.MainApplication.Companion.Soup
import com.example.boscobel_daily_activities.MainApplication.Companion.Soydre
import com.example.boscobel_daily_activities.MainApplication.Companion.Tartar
import com.example.boscobel_daily_activities.MainApplication.Companion.TomatoSauce
import com.example.boscobel_daily_activities.MainApplication.Companion.VenisonCurry
import com.example.boscobel_daily_activities.MainApplication.Companion.VenisonSteak
import com.example.boscobel_daily_activities.MainApplication.Companion.Whey
import com.example.boscobel_daily_activities.MainApplication.Companion.WhiteSource
import com.example.boscobel_daily_activities.MainApplication.Companion.mDay
import com.example.boscobel_daily_activities.MainApplication.Companion.mMonth
import com.example.boscobel_daily_activities.MainApplication.Companion.mYear
import java.lang.reflect.Array.set
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Year
import java.util.*
import java.util.jar.Attributes


// MainActivity is to set date and show process management menu.

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    private var _menuList: MutableList<MutableMap<String, String>> = mutableListOf()
    private val _from = arrayOf("name", "status")
    private val _to = intArrayOf(android.R.id.text1, android.R.id.text2)

    private var PositionBold: Int = -9999
    private var StatusBold: String? = null
    private var NameBold: String? = null

    private var switch: Int = 0  //表示系で、最初は０，項目を更新するときは１

//    private var d:Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var d:Date = Date()

        realm = Realm.getDefaultInstance()

        showDataPicker()

//        insertData()
    }

    private fun showDataPicker(){
// Set date to operate

        val calendar = Calendar.getInstance()
        val tYear = calendar.get(Calendar.YEAR)
        val tMonth = calendar.get(Calendar.MONTH)
        val tDay = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener() { view, year, month, dayOfMonth ->
                mYear = year
                mMonth = month
                mDay = dayOfMonth
                dataSet()
            },
            tYear ,
            tMonth,
            tDay)

        datePickerDialog.show()
        dataSet()

    }

    private fun dataSet(){
        //  Check the data of the date is already in DB and set data of the date if already exist, and set 0 if not.

        val realmResult = realm.where<CookingItem> ()
            .equalTo("Ryear", mYear)
            .equalTo("Rmonth", mMonth)
            .equalTo("Rday", mDay)
            .findFirst()

        if (realmResult != null){  //Already a data of the date is in database
            realm.executeTransaction{

                MAXCUSTOMER = realmResult.RMAXCUSTOMER

                Soup.Required = realmResult.SoupRequired
                Soup.Stock = realmResult.SoupStock
                Soup.Cook = realmResult.SoupCook
                Soup.Total = realmResult.SoupTotal

                PotatoSalad.Required = realmResult.PotatoSaladRequired
                PotatoSalad.Stock = realmResult.PotatoSaladStock
                PotatoSalad.Cook = realmResult.PotatoSaladCook
                PotatoSalad.Total = realmResult.PotatoSaladTotal

                CarrotRapee.Required = realmResult.CarrotRapeeRequired
                CarrotRapee.Stock = realmResult.CarrotRapeeStock
                CarrotRapee.Cook = realmResult.CarrotRapeeCook
                CarrotRapee.Total = realmResult.CarrotRapeeTotal

                Rice.Required = realmResult.RiceRequired
                Rice.Stock = realmResult.RiceStock
                Rice.Cook = realmResult.RiceCook
                Rice.Total = realmResult.RiceTotal

                CurryRice.Required = realmResult.reserve3Required
                CurryRice.Stock = realmResult.reserve3Stock
                CurryRice.Cook = realmResult.reserve3Cook
                CurryRice.Total = realmResult.reserve3Total

                CurryEgg.Required = realmResult.reserve4Required
                CurryEgg.Stock = realmResult.reserve4Stock
                CurryEgg.Cook = realmResult.reserve4Cook
                CurryEgg.Total = realmResult.reserve4Total

                Hamberg.Required = realmResult.HambergRequired
                Hamberg.Stock = realmResult.HambergStock
                Hamberg.Cook = realmResult.HambergCook
                Hamberg.Total = realmResult.HambergTotal

                Locomoko.Required = realmResult.LocomokoRequired
                Locomoko.Stock = realmResult.LocomokoStock
                Locomoko.Cook = realmResult.LocomokoCook
                Locomoko.Total = realmResult.LocomokTotal

                Sarmon.Required = realmResult.SarmonRequired
                Sarmon.Stock = realmResult.SarmonStock
                Sarmon.Cook = realmResult.SarmonCook
                Sarmon.Total = realmResult.SarmonTotal

                Chiken.Required = realmResult.ChikenRequired
                Chiken.Stock = realmResult.ChikenStock
                Chiken.Cook = realmResult.ChikenCook
                Chiken.Total = realmResult.ChikenTotal

                RoastBeef.Required = realmResult.RoastBeefRequired
                RoastBeef.Stock = realmResult.RoastBeefStock
                RoastBeef.Cook = realmResult.RoastBeefCook
                RoastBeef.Total = realmResult.RoastBeefTotal

                Pizza.Required = realmResult.PizzaRequired
                Pizza.Stock = realmResult.PizzaStock
                Pizza.Cook = realmResult.PizzaCook
                Pizza.Total = realmResult.PizzaTotal

                VenisonCurry.Required = realmResult.reserve1Required
                VenisonCurry.Stock = realmResult.reserve1Stock
                VenisonCurry.Cook = realmResult.reserve1Cook
                VenisonCurry.Total = realmResult.reserve1Total

                VenisonSteak.Required = realmResult.reserve2Required
                VenisonSteak.Stock = realmResult.reserve2Stock
                VenisonSteak.Cook = realmResult.reserve2Cook
                VenisonSteak.Total = realmResult.reserve2Total

                Gratin.Required = realmResult.reserve5Required
                Gratin.Stock = realmResult.reserve5Stock
                Gratin.Cook = realmResult.reserve5Cook
                Gratin.Total = realmResult.reserve5Total

                WhiteSource.Required = realmResult.reserve6Required
                WhiteSource.Stock = realmResult.reserve6Stock
                WhiteSource.Cook = realmResult.reserve6Cook
                WhiteSource.Total = realmResult.reserve6Total

                Bread.Required = realmResult.reserve7Required
                Bread.Stock = realmResult.reserve7Stock
                Bread.Cook = realmResult.reserve7Cook
                Bread.Total = realmResult.reserve7Total

                R8.Required = realmResult.reserve8Required
                R8.Stock = realmResult.reserve8Stock
                R8.Cook = realmResult.reserve8Cook
                R8.Total = realmResult.reserve8Total

                R9.Required = realmResult.reserve9Required
                R9.Stock = realmResult.reserve9Stock
                R9.Cook = realmResult.reserve9Cook
                R9.Total = realmResult.reserve9Total

                R0.Required = realmResult.reserve0Required
                R0.Stock = realmResult.reserve0Stock
                R0.Cook = realmResult.reserve9Cook
                R0.Total = realmResult.reserve0Total

                R11.Required = realmResult.reserve11Required
                R11.Stock = realmResult.reserve11Stock
                R11.Cook = realmResult.reserve11Cook
                R11.Total = realmResult.reserve11Total

                Mayonnaise.Needcook = realmResult.MayonnaiseNeedCook
                Tartar.Needcook = realmResult.TartarNeedCook
                Mayodre.Needcook = realmResult.MayodreNeedCook
                Soydre.Needcook = realmResult.SoydreNeedCCook
                Rapeedre.Needcook = realmResult.RapeedreNeedCook
                TomatoSauce.Needcook = realmResult.TomatoSauceNeedCook
                Balsamic.Needcook = realmResult.BalsamicNeedCook
                SmokeShoyu.Needcook = realmResult.SmokeShoyuNeedCook
                Whey.Needcook = realmResult.WheyNeedCook
                PizzaSauce.Needcook = realmResult.Reserve1NeedCook
                Raisin.Needcook = realmResult.Reserve2NeedCook
                Blueberry.Needcook = realmResult.Reserve3NeedCook
                RR4.Needcook = realmResult.Reserve4NeedCook
                RR5.Needcook = realmResult.Reserve5NeedCook
                RR6.Needcook = realmResult.Reserve6NeedCook
                RR7.Needcook = realmResult.Reserve7NeedCook
                RR8.Needcook = realmResult.Reserve8NeedCoook
                RR9.Needcook = realmResult.Reserve9NeedCook


            }
        } else if (realmResult == null){
            // if date is new, set all value to zero for start
            MAXCUSTOMER = 0

            Soup.Required = 0
            Soup.Stock = 0
            Soup.Cook = 0
            Soup.Total = 0

            PotatoSalad.Required = 0
            PotatoSalad.Stock = 0
            PotatoSalad.Cook = 0
            PotatoSalad.Total = 0

            CarrotRapee.Required = 0
            CarrotRapee.Stock = 0
            CarrotRapee.Cook = 0
            CarrotRapee.Total = 0

            Rice.Required = 0
            Rice.Stock = 0
            Rice.Cook = 0
            Rice.Total = 0

            CurryRice.Required = 0
            CurryRice.Stock = 0
            CurryRice.Cook = 0
            CurryRice.Total = 0

            CurryEgg.Required = 0
            CurryEgg.Stock = 0
            CurryEgg.Cook = 0
            CurryEgg.Total = 0

            Hamberg.Required = 0
            Hamberg.Stock = 0
            Hamberg.Cook = 0
            Hamberg.Total = 0

            Locomoko.Required = 0
            Locomoko.Stock = 0
            Locomoko.Cook = 0
            Locomoko.Total = 0

            Sarmon.Required = 0
            Sarmon.Stock = 0
            Sarmon.Cook = 0
            Sarmon.Total = 0

            Chiken.Required = 0
            Chiken.Stock = 0
            Chiken.Cook = 0
            Chiken.Total = 0

            RoastBeef.Required = 0
            RoastBeef.Stock = 0
            RoastBeef.Cook = 0
            RoastBeef.Total = 0

            Pizza.Required = 0
            Pizza.Stock = 0
            Pizza.Cook = 0
            Pizza.Total = 0

            VenisonCurry.Required = 0
            VenisonCurry.Stock = 0
            VenisonCurry.Cook = 0
            VenisonCurry.Total = 0

            VenisonSteak.Required = 0
            VenisonSteak.Stock = 0
            VenisonSteak.Cook = 0
            VenisonSteak.Total = 0

            Gratin.Required = 0
            Gratin.Stock = 0
            Gratin.Cook = 0
            Gratin.Total = 0

            WhiteSource.Required = 0
            WhiteSource.Stock = 0
            WhiteSource.Cook = 0
            WhiteSource.Total = 0

            Bread.Required = 0
            Bread.Stock = 0
            Bread.Cook = 0
            Bread.Total = 0

            R8.Required = 0
            R8.Stock = 0
            R8.Cook = 0
            R8.Total = 0

            R9.Required = 0
            R9.Stock = 0
            R9.Cook = 0
            R9.Total = 0

            R0.Required = 0
            R0.Stock = 0
            R0.Cook = 0
            R0.Total = 0

            R11.Required = 0
            R11.Stock = 0
            R11.Cook = 0
            R11.Total = 0

            R12.Required = 0
            R12.Stock = 0
            R12.Cook = 0
            R12.Total = 0

            Mayonnaise.Needcook = 0
            Tartar.Needcook = 0
            Mayodre.Needcook = 0
            Soydre.Needcook = 0
            Rapeedre.Needcook = 0
            TomatoSauce.Needcook = 0
            Balsamic.Needcook = 0
            SmokeShoyu.Needcook = 0
            Whey.Needcook = 0
            PizzaSauce.Needcook = 0
            Raisin.Needcook = 0
            Blueberry.Needcook = 0
            RR4.Needcook = 0
            RR5.Needcook = 0
            RR6.Needcook = 0
            RR7.Needcook = 0
            RR8.Needcook = 0
            RR9.Needcook = 0

        }

// Treat Process data from here
        val realmResult2 = realm.where<ProcessManagement> ()
            .equalTo("Ryear", mYear)
            .equalTo("Rmonth", mMonth)
            .equalTo("Rday", mDay)
            .findFirst()

        if (realmResult2 != null){  //Already a data of the date is in database

            _menuList = restore_menu()

        } else if (realmResult2 == null){

            _menuList = create_menu()
        }

        // Display updated menu with updated status
        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        val adapter = SimpleAdapter(
            this@MainActivity,
            _menuList,
            android.R.layout.simple_list_item_2,
            _from,
            _to
        )
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()

    }

    private fun restore_menu(): MutableList<MutableMap<String, String>>{
// Check process management of the day exist in DB and update menu if exist
        val realmResult2 = realm.where<ProcessManagement> ()
            .equalTo("Ryear", mYear)
            .equalTo("Rmonth", mMonth)
            .equalTo("Rday", mDay)
            .findFirst()

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf<String, String>()

        realm.executeTransaction {
            if (realmResult2?.item0 != null || realmResult2?.status0 != null){
                menu = mutableMapOf("name" to realmResult2!!.item0, "status" to realmResult2.status0)
                menuList.add(menu)}

            if (realmResult2?.item1 != null || realmResult2?.status1 != null){
                menu = mutableMapOf("name" to realmResult2!!.item1, "status" to realmResult2.status1)
                menuList.add(menu)}

            if (realmResult2?.item2 != null || realmResult2?.status2 != null){
                menu = mutableMapOf("name" to realmResult2!!.item2, "status" to realmResult2.status2)
                menuList.add(menu)}

            if (realmResult2?.item3 != null || realmResult2?.status3 != null){
                menu = mutableMapOf("name" to realmResult2!!.item3, "status" to realmResult2.status3)
                menuList.add(menu)}

            if (realmResult2?.item4 != null || realmResult2?.status4 != null){
                menu = mutableMapOf("name" to realmResult2!!.item4, "status" to realmResult2.status4)
                menuList.add(menu)}

            if (realmResult2?.item5 != null || realmResult2?.status5 != null){
                menu = mutableMapOf("name" to realmResult2!!.item5, "status" to realmResult2.status5)
                menuList.add(menu)}

            if (realmResult2?.item6 != null || realmResult2?.status6 != null){
                menu = mutableMapOf("name" to realmResult2!!.item6, "status" to realmResult2.status6)
                menuList.add(menu)}

            if (realmResult2?.item7 != null || realmResult2?.status7 != null){
                menu = mutableMapOf("name" to realmResult2!!.item7, "status" to realmResult2.status7)
                menuList.add(menu)}

            if (realmResult2?.item8 != null || realmResult2?.status8 != null){
                menu = mutableMapOf("name" to realmResult2!!.item8, "status" to realmResult2.status8)
                menuList.add(menu)}

            if (realmResult2?.item9 != null || realmResult2?.status9 != null){
                menu = mutableMapOf("name" to realmResult2!!.item9, "status" to realmResult2.status9)
                menuList.add(menu)}

            if (realmResult2?.item10 != null || realmResult2?.status10 != null){
                menu = mutableMapOf("name" to realmResult2!!.item10, "status" to realmResult2.status10)
                menuList.add(menu)}

            if (realmResult2?.item11 != null || realmResult2?.status11 != null){
                menu = mutableMapOf("name" to realmResult2!!.item11, "status" to realmResult2.status11)
                menuList.add(menu)}

            if (realmResult2?.item12 != null || realmResult2?.status12 != null){
                menu = mutableMapOf("name" to realmResult2!!.item12, "status" to realmResult2.status12)
                menuList.add(menu)}

            if (realmResult2?.item13 != null || realmResult2?.status13 != null){
                menu = mutableMapOf("name" to realmResult2!!.item13, "status" to realmResult2.status13)
                menuList.add(menu)}

            if (realmResult2?.item14 != null || realmResult2?.status14 != null){
                menu = mutableMapOf("name" to realmResult2!!.item14, "status" to realmResult2.status14)
                menuList.add(menu)}

            if (realmResult2?.item15 != null || realmResult2?.status15 != null){
                menu = mutableMapOf("name" to realmResult2!!.item15, "status" to realmResult2.status15)
                menuList.add(menu)}

            if (realmResult2?.item16 != null || realmResult2?.status16 != null){
                menu = mutableMapOf("name" to realmResult2!!.item16, "status" to realmResult2.status16)
                menuList.add(menu)}

            if (realmResult2?.item17 != null || realmResult2?.status17 != null){
                menu = mutableMapOf("name" to realmResult2!!.item17, "status" to realmResult2.status17)
                menuList.add(menu)}

            if (realmResult2?.item18 != null || realmResult2?.status18 != null){
                menu = mutableMapOf("name" to realmResult2!!.item18, "status" to realmResult2.status18)
                menuList.add(menu)}

            if (realmResult2?.item19 != null || realmResult2?.status19 != null){
                menu = mutableMapOf("name" to realmResult2!!.item19, "status" to realmResult2.status19)
                menuList.add(menu)}

            if (realmResult2?.item20 != null || realmResult2?.status20 != null){
                menu = mutableMapOf("name" to realmResult2!!.item20, "status" to realmResult2.status20)
                menuList.add(menu)}

            if (realmResult2?.item21 != null || realmResult2?.status21 != null){
                menu = mutableMapOf("name" to realmResult2!!.item21, "status" to realmResult2.status21)
                menuList.add(menu)}

            if (realmResult2?.item22 != null || realmResult2?.status22 != null){
                menu = mutableMapOf("name" to realmResult2!!.item22, "status" to realmResult2.status22)
                menuList.add(menu)}

        }
        return menuList
    }


    private fun create_menu(): MutableList<MutableMap<String, String>> {
// Create action list

        val Done: String = "完了"
        val NotDone: String = "未完了"
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf<String, String>()

        menu = mutableMapOf("name" to "手洗い、頭巾装着", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ゴミ箱、ゴミネット準備", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "布巾、手ぬぐいタオルセット", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "パン調合開始", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "冷蔵庫温度、保管状態確認","status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "数量設定", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "解凍処理", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ソース類取り出し", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ケーキ類取り出し", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ジャガイモ、卵をゆでる", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "炊飯器セット", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜類処理準備", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ポテサラ用人参をゆでる", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "燻製開始", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ピザ野菜準備", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ドレッシング類準備", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "スープ準備", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ポテサラ仕上げ", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "ベーコンカット","status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "鉄板、オーブン余熱", "status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "冷蔵トレーセット","status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "従業員健康チェック","status" to NotDone)
        menuList.add(menu)
        menu = mutableMapOf("name" to "トイレ洗浄、消毒処理", "status" to NotDone)
        menuList.add(menu)


        switch = 1
        return menuList
    }


    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        // Invoke Status input when clicked on action menu
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val processName = item["name"]
            val processStatus = item["status"]

            PositionBold = position
            NameBold = processName
            StatusBold = processStatus

            val intent2StatusCheck = Intent(
                this@MainActivity,
                StatusCheckActivity::class.java
            )

            intent2StatusCheck.putExtra("processName", processName)
            intent2StatusCheck.putExtra("processStatus", processStatus)
            intent2StatusCheck.putExtra("position", position)

            val requestCode: Int = 1000
            startActivityForResult(intent2StatusCheck, requestCode)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
// Set Status of action
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK &&
            requestCode == 1000 && intent != null
        ) {
            val response = data?.getStringExtra("returnstatus")
            StatusBold = response
        }
    }

    override fun onStart() {
        super.onStart()
        var menu = mutableMapOf<String, String>()

        if (switch == 0) {
            _menuList = create_menu()
        }
        if (switch == 1) {
            if (NameBold != null && PositionBold != -9999 && StatusBold != null) {
                menu =
                    mutableMapOf("name" to NameBold.toString(), "status" to StatusBold.toString())
                _menuList[PositionBold] = menu
            }

        }


        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        val adapter = SimpleAdapter(
            this@MainActivity,
            _menuList,
            android.R.layout.simple_list_item_2,
            _from,
            _to
        )
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    public  fun Main2ActivityKick() {
// Invoke Activity of Cooking item and Seasoning number
        val intent = Intent(this, MainActivity2::class.java)
//            val intent = Intent(applicationContext, MainActivity2::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        /*        when (item.itemId) {
                    R.id.menuListOptionDairyCul ->
                        Log.d("uztest", "DairyCul is selected")
                    //MainActivity2起動
                    Main2ActivityKick()
                    else ->
                        returnVal = super.onOptionsItemSelected(item)
                }

         */

        if (item.itemId == R.id.menuListOptionDairyCul) {
            Main2ActivityKick()
        }

        if (item.itemId == R.id.menuListDateSave) {
            val realmResult = realm.where<CookingItem> ()
                .equalTo("Ryear", mYear)
                .equalTo("Rmonth", mMonth)
                .equalTo("Rday", mDay)
                .findFirst()

            if (realmResult==null){
                realm.executeTransaction() {
                    //          Log.d("uztest", "Date of data is not in DB")
                    val id = realm.where<CookingItem>().max("id")
                    //           Log.d("uztest", "realm id read")
                    val nextId = (id?.toLong() ?: 0) + 1
                    //           Log.d("uztest", "next id = ${nextId}")
                    val realmObject1 = realm.createObject<CookingItem>(nextId)
                    //            Log.d("uztest", "realm new id created")

                    realmObject1.Ryear = mYear
                    realmObject1.Rmonth = mMonth
                    realmObject1.Rday = mDay

                    realmObject1.RMAXCUSTOMER = MAXCUSTOMER

                    realmObject1.SoupRequired = Soup.Required
                    realmObject1.SoupStock = Soup.Stock
                    realmObject1.SoupCook = Soup.Cook
                    realmObject1.SoupTotal = Soup.Total

                    realmObject1.PotatoSaladRequired = PotatoSalad.Required
                    realmObject1.PotatoSaladStock = PotatoSalad.Stock
                    realmObject1.PotatoSaladCook = PotatoSalad.Cook
                    realmObject1.PotatoSaladTotal = PotatoSalad.Total

                    realmObject1.CarrotRapeeRequired = CarrotRapee.Required
                    realmObject1.CarrotRapeeStock = CarrotRapee.Stock
                    realmObject1.CarrotRapeeCook = CarrotRapee.Cook
                    realmObject1.CarrotRapeeTotal = CarrotRapee.Total

                    realmObject1.RiceRequired = Rice.Required
                    realmObject1.RiceStock = Rice.Stock
                    realmObject1.RiceCook = Rice.Cook
                    realmObject1.RiceTotal = Rice.Total

                    realmObject1.reserve3Required = CurryRice.Required
                    realmObject1.reserve3Stock = CurryRice.Stock
                    realmObject1.reserve3Cook = CurryRice.Cook
                    realmObject1.reserve3Total = CurryRice.Total

                    realmObject1.reserve4Required = CurryEgg.Required
                    realmObject1.reserve4Stock = CurryEgg.Stock
                    realmObject1.reserve4Cook = CurryEgg.Cook
                    realmObject1.reserve4Total = CurryEgg.Total

                    realmObject1.HambergRequired = Hamberg.Required
                    realmObject1.HambergStock = Hamberg.Stock
                    realmObject1.HambergCook = Hamberg.Cook
                    realmObject1.HambergTotal = Hamberg.Total

                    realmObject1.LocomokoRequired = Locomoko.Required
                    realmObject1.LocomokoStock = Locomoko.Stock
                    realmObject1.LocomokoCook = Locomoko.Cook
                    realmObject1.LocomokTotal = Locomoko.Total

                    realmObject1.SarmonRequired = Sarmon.Required
                    realmObject1.SarmonStock = Sarmon.Stock
                    realmObject1.SarmonCook = Sarmon.Cook
                    realmObject1.SarmonTotal = Sarmon.Total

                    realmObject1.ChikenRequired = Chiken.Required
                    realmObject1.ChikenStock = Chiken.Stock
                    realmObject1.ChikenCook = Chiken.Cook
                    realmObject1.ChikenTotal = Chiken.Total

                    realmObject1.RoastBeefRequired = RoastBeef.Required
                    realmObject1.RoastBeefStock = RoastBeef.Stock
                    realmObject1.RoastBeefCook = RoastBeef.Cook
                    realmObject1.RoastBeefTotal = RoastBeef.Total

                    realmObject1.PizzaRequired = Pizza.Required
                    realmObject1.PizzaStock = Pizza.Stock
                    realmObject1.PizzaCook = Pizza.Cook
                    realmObject1.PizzaTotal = Pizza.Total

                    realmObject1.reserve1Required = VenisonCurry.Required
                    realmObject1.reserve1Stock = VenisonCurry.Stock
                    realmObject1.reserve1Cook = VenisonCurry.Cook
                    realmObject1.reserve1Total = VenisonCurry.Total

                    realmObject1.reserve2Required = VenisonSteak.Required
                    realmObject1.reserve2Stock = VenisonSteak.Stock
                    realmObject1.reserve2Cook = VenisonSteak.Cook
                    realmObject1.reserve2Total = VenisonSteak.Total

                    realmObject1.reserve5Required = Gratin.Required
                    realmObject1.reserve5Stock = Gratin.Stock
                    realmObject1.reserve5Cook = Gratin.Cook
                    realmObject1.reserve5Total = Gratin.Total

                    realmObject1.reserve6Required = WhiteSource.Required
                    realmObject1.reserve6Stock = WhiteSource.Stock
                    realmObject1.reserve6Cook = WhiteSource.Cook
                    realmObject1.reserve6Total = WhiteSource.Total

                    realmObject1.reserve7Required = Bread.Required
                    realmObject1.reserve7Stock = Bread.Stock
                    realmObject1.reserve7Cook = Bread.Cook
                    realmObject1.reserve7Total = Bread.Total

                    realmObject1.reserve8Required = R8.Required
                    realmObject1.reserve8Stock = R8.Stock
                    realmObject1.reserve8Cook = R8.Cook
                    realmObject1.reserve8Total = R8.Total

                    realmObject1.reserve9Required = R9.Required
                    realmObject1.reserve9Stock = R9.Stock
                    realmObject1.reserve9Cook = R9.Cook
                    realmObject1.reserve9Total = R9.Total

                    realmObject1.reserve0Required = R0.Required
                    realmObject1.reserve0Stock = R0.Stock
                    realmObject1.reserve0Cook = R0.Cook
                    realmObject1.reserve9Total = R0.Total

                    realmObject1.reserve11Required = R11.Required
                    realmObject1.reserve11Stock = R11.Stock
                    realmObject1.reserve11Cook = R11.Cook
                    realmObject1.reserve11Total = R11.Total

                    realmObject1.reserve12Required = R12.Required
                    realmObject1.reserve12Stock = R12.Stock
                    realmObject1.reserve12Cook = R12.Cook
                    realmObject1.reserve12Total = R12.Total

                    realmObject1.MayonnaiseNeedCook = Mayonnaise.Needcook
                    realmObject1.TartarNeedCook = Tartar.Needcook
                    realmObject1.MayodreNeedCook = Mayodre.Needcook
                    realmObject1.SoydreNeedCCook = Soydre.Needcook
                    realmObject1.RapeedreNeedCook = Rapeedre.Needcook
                    realmObject1.TomatoSauceNeedCook = TomatoSauce.Needcook
                    realmObject1.BalsamicNeedCook = Balsamic.Needcook
                    realmObject1.SmokeShoyuNeedCook = SmokeShoyu.Needcook
                    realmObject1.WheyNeedCook = Whey.Needcook
                    realmObject1.Reserve1NeedCook = PizzaSauce.Needcook
                    realmObject1.Reserve2NeedCook = Raisin.Needcook
                    realmObject1.Reserve3NeedCook = Blueberry.Needcook
                    realmObject1.Reserve4NeedCook = RR4.Needcook
                    realmObject1.Reserve5NeedCook = RR5.Needcook
                    realmObject1.Reserve6NeedCook = RR6.Needcook
                    realmObject1.Reserve7NeedCook = RR7.Needcook
                    realmObject1.Reserve8NeedCoook = RR8.Needcook
                    realmObject1.Reserve9NeedCook = RR9.Needcook

                }
            }
            else if (realmResult != null){
                Log.d("uztest","Date of data is already in DB")

                realm.executeTransaction() {

                    realmResult.RMAXCUSTOMER = MAXCUSTOMER
                    realmResult.SoupRequired= Soup.Required
                    realmResult.SoupStock = Soup.Stock
                    realmResult.SoupCook = Soup.Cook
                    realmResult.SoupTotal = Soup.Total

                    realmResult.PotatoSaladRequired = PotatoSalad.Required
                    realmResult.PotatoSaladStock = PotatoSalad.Stock
                    realmResult.PotatoSaladCook = PotatoSalad.Cook
                    realmResult.PotatoSaladTotal = PotatoSalad.Total

                    realmResult.CarrotRapeeRequired = CarrotRapee.Required
                    realmResult.CarrotRapeeStock = CarrotRapee.Stock
                    realmResult.CarrotRapeeCook = CarrotRapee.Cook
                    realmResult.CarrotRapeeTotal = CarrotRapee.Total

                    realmResult.RiceRequired = Rice.Required
                    realmResult.RiceStock = Rice.Stock
                    realmResult.RiceCook = Rice.Cook
                    realmResult.RiceTotal = Rice.Total

                    realmResult.reserve3Required = CurryRice.Required
                    realmResult.reserve3Stock = CurryRice.Stock
                    realmResult.reserve3Cook = CurryRice.Cook
                    realmResult.reserve3Total = CurryRice.Total

                    realmResult.reserve4Required = CurryEgg.Required
                    realmResult.reserve4Stock = CurryEgg.Stock
                    realmResult.reserve4Cook = CurryEgg.Cook
                    realmResult.reserve4Total = CurryEgg.Total

                    realmResult.HambergRequired = Hamberg.Required
                    realmResult.HambergStock = Hamberg.Stock
                    realmResult.HambergCook = Hamberg.Cook
                    realmResult.HambergTotal = Hamberg.Total

                    realmResult.LocomokoRequired = Locomoko.Required
                    realmResult.LocomokoStock = Locomoko.Stock
                    realmResult.LocomokoCook = Locomoko.Cook
                    realmResult.LocomokTotal = Locomoko.Total

                    realmResult.SarmonRequired = Sarmon.Required
                    realmResult.SarmonStock = Sarmon.Stock
                    realmResult.SarmonCook = Sarmon.Cook
                    realmResult.SarmonTotal = Sarmon.Total

                    realmResult.ChikenRequired = Chiken.Required
                    realmResult.ChikenStock = Chiken.Stock
                    realmResult.ChikenCook = Chiken.Cook
                    realmResult.ChikenTotal = Chiken.Total

                    realmResult.RoastBeefRequired = RoastBeef.Required
                    realmResult.RoastBeefStock = RoastBeef.Stock
                    realmResult.RoastBeefCook = RoastBeef.Cook
                    realmResult.RoastBeefTotal = RoastBeef.Total

                    realmResult.PizzaRequired = Pizza.Required
                    realmResult.PizzaStock = Pizza.Stock
                    realmResult.PizzaCook = Pizza.Cook
                    realmResult.PizzaTotal = Pizza.Total

                    realmResult.reserve1Required = VenisonCurry.Required
                    realmResult.reserve1Stock = VenisonCurry.Stock
                    realmResult.reserve1Cook = VenisonCurry.Cook
                    realmResult.reserve1Total = VenisonCurry.Total

                    realmResult.reserve2Required = VenisonSteak.Required
                    realmResult.reserve2Stock = VenisonSteak.Stock
                    realmResult.reserve2Cook = VenisonSteak.Cook
                    realmResult.reserve2Total = VenisonSteak.Total

                    realmResult.reserve5Required = Gratin.Required
                    realmResult.reserve5Stock = Gratin.Stock
                    realmResult.reserve7Cook = Gratin.Cook
                    realmResult.reserve7Total = Gratin.Total

                    realmResult.reserve6Required = WhiteSource.Required
                    realmResult.reserve6Stock = WhiteSource.Stock
                    realmResult.reserve6Cook = WhiteSource.Cook
                    realmResult.reserve6Total = WhiteSource.Total

                    realmResult.reserve7Required = Bread.Required
                    realmResult.reserve7Stock = Bread.Stock
                    realmResult.reserve7Cook = Bread.Cook
                    realmResult.reserve7Total = Bread.Total

                    realmResult.reserve8Required = R8.Required
                    realmResult.reserve8Stock = R8.Stock
                    realmResult.reserve8Cook = R8.Cook
                    realmResult.reserve8Total = R8.Total

                    realmResult.reserve9Required = R9.Required
                    realmResult.reserve9Stock = R9.Stock
                    realmResult.reserve9Cook = R9.Cook
                    realmResult.reserve9Total = R9.Total

                    realmResult.reserve0Required = R0.Required
                    realmResult.reserve0Stock = R0.Stock
                    realmResult.reserve0Cook = R0.Cook
                    realmResult.reserve0Total = R0.Total

                    realmResult.reserve11Required = R11.Required
                    realmResult.reserve11Stock = R11.Stock
                    realmResult.reserve11Cook = R11.Cook
                    realmResult.reserve11Total = R11.Total

                    realmResult.reserve12Required = R12.Required
                    realmResult.reserve12Stock = R12.Stock
                    realmResult.reserve12Cook = R12.Cook
                    realmResult.reserve12Total = R12.Total

                    realmResult.MayonnaiseNeedCook = Mayonnaise.Needcook
                    realmResult.TartarNeedCook = Tartar.Needcook
                    realmResult.MayodreNeedCook = Mayodre.Needcook
                    realmResult.SoydreNeedCCook = Soydre.Needcook
                    realmResult.RapeedreNeedCook = Rapeedre.Needcook
                    realmResult.TomatoSauceNeedCook = TomatoSauce.Needcook
                    realmResult.BalsamicNeedCook = Balsamic.Needcook
                    realmResult.SmokeShoyuNeedCook = SmokeShoyu.Needcook
                    realmResult.WheyNeedCook = Whey.Needcook
                    realmResult.Reserve1NeedCook = PizzaSauce.Needcook
                    realmResult.Reserve2NeedCook = Raisin.Needcook
                    realmResult.Reserve3NeedCook = Blueberry.Needcook
                    realmResult.Reserve4NeedCook = RR4.Needcook
                    realmResult.Reserve5NeedCook = RR5.Needcook
                    realmResult.Reserve6NeedCook = RR6.Needcook
                    realmResult.Reserve7NeedCook = RR7.Needcook
                    realmResult.Reserve8NeedCoook = RR8.Needcook
                    realmResult.Reserve9NeedCook = RR9.Needcook
                }
            }
            val realmResult2 = realm.where<ProcessManagement> ()
                .equalTo("Ryear", mYear)
                .equalTo("Rmonth", mMonth)
                .equalTo("Rday", mDay)
                .findFirst()

            if (realmResult2==null){
                //Create new record
                realm.executeTransaction() {
                    //          Log.d("uztest", "Date of data is not in DB")
                    val id = realm.where<ProcessManagement>().max("id")
                    //           Log.d("uztest", "realm id read")
                    val nextId = (id?.toLong() ?: 0) + 1
                    //           Log.d("uztest", "next id = ${nextId}")
                    val realmObject2 = realm.createObject<ProcessManagement>(nextId)
                    //            Log.d("uztest", "realm new id created")

                    realmObject2.Ryear = mYear
                    realmObject2.Rmonth = mMonth
                    realmObject2.Rday = mDay

                    if (_menuList.size > 0) {
                        val item = _menuList[0] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item0 = processName.toString()
                        realmObject2.status0 = processStatus.toString()
                    }

                    if (_menuList.size > 1) {
                        val item = _menuList[1] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item1 = processName.toString()
                        realmObject2.status1 = processStatus.toString()
                    }

                    if (_menuList.size > 2) {
                        val item = _menuList[2] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item2 = processName.toString()
                        realmObject2.status2 = processStatus.toString()
                    }

                    if (_menuList.size > 3) {
                        val item = _menuList[3] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item3 = processName.toString()
                        realmObject2.status3 = processStatus.toString()
                    }

                    if (_menuList.size > 4) {
                        val item = _menuList[4] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item4 = processName.toString()
                        realmObject2.status4 = processStatus.toString()
                    }

                    if (_menuList.size > 5) {
                        val item = _menuList[5] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item5 = processName.toString()
                        realmObject2.status5 = processStatus.toString()
                    }

                    if (_menuList.size > 6) {
                        val item = _menuList[6] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item6 = processName.toString()
                        realmObject2.status6 = processStatus.toString()
                    }

                    if (_menuList.size > 7) {
                        val item = _menuList[7] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item7 = processName.toString()
                        realmObject2.status7 = processStatus.toString()
                    }

                    if (_menuList.size > 8) {
                        val item = _menuList[8] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item8 = processName.toString()
                        realmObject2.status8 = processStatus.toString()
                    }

                    if (_menuList.size > 9) {
                        val item = _menuList[9] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item9 = processName.toString()
                        realmObject2.status9 = processStatus.toString()
                    }

                    if (_menuList.size > 10) {
                        val item = _menuList[10] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item10 = processName.toString()
                        realmObject2.status10 = processStatus.toString()
                    }

                    if (_menuList.size > 11) {
                        val item = _menuList[11] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item11 = processName.toString()
                        realmObject2.status11 = processStatus.toString()
                    }

                    if (_menuList.size > 12) {
                        val item = _menuList[12] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item12 = processName.toString()
                        realmObject2.status12 = processStatus.toString()
                    }

                    if (_menuList.size > 13) {
                        val item = _menuList[13] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item13 = processName.toString()
                        realmObject2.status13 = processStatus.toString()
                    }

                    if (_menuList.size > 14) {
                        val item = _menuList[14] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item14 = processName.toString()
                        realmObject2.status14 = processStatus.toString()
                    }

                    if (_menuList.size > 15) {
                        val item = _menuList[15] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item15 = processName.toString()
                        realmObject2.status15 = processStatus.toString()
                    }

                    if (_menuList.size > 16) {
                        val item = _menuList[16] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item16 = processName.toString()
                        realmObject2.status16 = processStatus.toString()
                    }

                    if (_menuList.size > 17) {
                        val item = _menuList[17] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item17 = processName.toString()
                        realmObject2.status17 = processStatus.toString()
                    }

                    if (_menuList.size > 18) {
                        val item = _menuList[18] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item18 = processName.toString()
                        realmObject2.status18 = processStatus.toString()
                    }

                    if (_menuList.size > 19) {
                        val item = _menuList[19] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item19 = processName.toString()
                        realmObject2.status19 = processStatus.toString()
                    }

                    if (_menuList.size > 20) {
                        val item = _menuList[20] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item20 = processName.toString()
                        realmObject2.status20 = processStatus.toString()
                    }

                    if (_menuList.size > 21) {
                        val item = _menuList[21] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item21 = processName.toString()
                        realmObject2.status21 = processStatus.toString()
                    }

                    if (_menuList.size > 22) {
                        val item = _menuList[22] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmObject2.item22 = processName.toString()
                        realmObject2.status22 = processStatus.toString()
                    }

                }
            }
            else if (realmResult2 != null){
                // Update existing record
                Log.d("uztest","Date of data is already in DB")

                realm.executeTransaction() {
                    if (_menuList.size > 0) {
                        val item = _menuList[0] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item0 = processName.toString()
                        realmResult2.status0 = processStatus.toString()
                    }


                    if (_menuList.size > 1) {
                        val item = _menuList[1] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item1 = processName.toString()
                        realmResult2.status1 = processStatus.toString()
                    }

                    if (_menuList.size > 2) {
                        val item = _menuList[2] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item2 = processName.toString()
                        realmResult2.status2 = processStatus.toString()
                    }

                    if (_menuList.size > 3) {
                        val item = _menuList[3] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item3 = processName.toString()
                        realmResult2.status3 = processStatus.toString()
                    }

                    if (_menuList.size > 4) {
                        val item = _menuList[4] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item4 = processName.toString()
                        realmResult2.status4 = processStatus.toString()
                    }

                    if (_menuList.size > 5) {
                        val item = _menuList[5] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item5 = processName.toString()
                        realmResult2.status5 = processStatus.toString()
                    }

                    if (_menuList.size > 6) {
                        val item = _menuList[6] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item6 = processName.toString()
                        realmResult2.status6 = processStatus.toString()
                    }

                    if (_menuList.size > 7) {
                        val item = _menuList[7] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item7 = processName.toString()
                        realmResult2.status7 = processStatus.toString()
                    }
                    if (_menuList.size > 8) {
                        val item = _menuList[8] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item8 = processName.toString()
                        realmResult2.status8 = processStatus.toString()
                    }

                    if (_menuList.size > 9) {
                        val item = _menuList[9] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item9 = processName.toString()
                        realmResult2.status9 = processStatus.toString()
                    }

                    if (_menuList.size > 10) {
                        val item = _menuList[10] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item10 = processName.toString()
                        realmResult2.status10 = processStatus.toString()
                    }

                    if (_menuList.size > 11) {
                        val item = _menuList[11] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item11 = processName.toString()
                        realmResult2.status11 = processStatus.toString()
                    }

                    if (_menuList.size > 12) {
                        val item = _menuList[12] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item12 = processName.toString()
                        realmResult2.status12 = processStatus.toString()
                    }

                    if (_menuList.size > 13) {
                        val item = _menuList[13] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item13 = processName.toString()
                        realmResult2.status13 = processStatus.toString()
                    }

                    if (_menuList.size > 14) {
                        val item = _menuList[14] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item14 = processName.toString()
                        realmResult2.status14 = processStatus.toString()
                    }

                    if (_menuList.size > 15) {
                        val item = _menuList[15] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item15 = processName.toString()
                        realmResult2.status15 = processStatus.toString()
                    }

                    if (_menuList.size > 16) {
                        val item = _menuList[16] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item16 = processName.toString()
                        realmResult2.status16 = processStatus.toString()
                    }

                    if (_menuList.size > 17) {
                        val item = _menuList[17] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item17 = processName.toString()
                        realmResult2.status17 = processStatus.toString()
                    }

                    if (_menuList.size > 18) {
                        val item = _menuList[18] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item18 = processName.toString()
                        realmResult2.status18 = processStatus.toString()
                    }

                    if (_menuList.size > 19) {
                        val item = _menuList[19] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item19 = processName.toString()
                        realmResult2.status19 = processStatus.toString()
                    }

                    if (_menuList.size > 20) {
                        val item = _menuList[20] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item20 = processName.toString()
                        realmResult2.status20 = processStatus.toString()
                    }

                    if (_menuList.size > 21) {
                        val item = _menuList[21] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item21 = processName.toString()
                        realmResult2.status21 = processStatus.toString()
                    }

                    if (_menuList.size > 22) {
                        val item = _menuList[22] as MutableMap<String, String>
                        val processName = item["name"]
                        val processStatus = item["status"]
                        realmResult2.item22 = processName.toString()
                        realmResult2.status22 = processStatus.toString()
                    }

                }
            }
        }


        if (item.itemId == R.id.menuListDateInput) {
            showDataPicker()
            dataSet()
        }

        return returnVal
    }

}