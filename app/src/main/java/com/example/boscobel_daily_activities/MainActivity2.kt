package com.example.boscobel_daily_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import io.realm.kotlin.createObject
import io.realm.kotlin.where
//import kotlinx.android.synthetic.main.*
//import kotlinx.android.synthetic.main.activity_main2.*

// MainActivity2 is for input number of each day preparation.  It has two fragments, Dish and seasoning.

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    /* For menu on dish number input (Menu is for zero clear and data save)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_list2, menu)
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
        if (item.itemId == R.id.menuListZeroClear )
        {   //zero clear
            MainApplication.MAXCUSTOMER = 0

            MainApplication.Soup.Required = 0
            MainApplication.Soup.Stock = 0
            MainApplication.Soup.Cook = 0
            MainApplication.Soup.Total = 0

            MainApplication.PotatoSalad.Required = 0
            MainApplication.PotatoSalad.Stock = 0
            MainApplication.PotatoSalad.Cook = 0
            MainApplication.PotatoSalad.Total = 0

            MainApplication.CarrotRapee.Required = 0
            MainApplication.CarrotRapee.Stock = 0
            MainApplication.CarrotRapee.Cook = 0
            MainApplication.CarrotRapee.Total = 0

            MainApplication.Rice.Required = 0
            MainApplication.Rice.Stock = 0
            MainApplication.Rice.Cook = 0
            MainApplication.Rice.Total = 0

            MainApplication.Hamberg.Required = 0
            MainApplication.Hamberg.Stock = 0
            MainApplication.Hamberg.Cook = 0
            MainApplication.Hamberg.Total = 0

            MainApplication.Locomoko.Required = 0
            MainApplication.Locomoko.Stock = 0
            MainApplication.Locomoko.Cook = 0
            MainApplication.Locomoko.Total = 0

            MainApplication.Sarmon.Required = 0
            MainApplication.Sarmon.Stock = 0
            MainApplication.Sarmon.Cook = 0
            MainApplication.Sarmon.Total = 0

            MainApplication.Chiken.Required = 0
            MainApplication.Chiken.Stock = 0
            MainApplication.Chiken.Cook = 0
            MainApplication.Chiken.Total = 0

            MainApplication.RoastBeef.Required = 0
            MainApplication.RoastBeef.Stock = 0
            MainApplication.RoastBeef.Cook = 0
            MainApplication.RoastBeef.Total = 0

            MainApplication.Mayonnaise.Needcook = 0
            MainApplication.Tartar.Needcook = 0
            MainApplication.Mayodre.Needcook = 0
            MainApplication.Soydre.Needcook = 0
            MainApplication.Rapeedre.Needcook = 0
            MainApplication.TomatoSauce.Needcook = 0
            MainApplication.Balsamic.Needcook = 0
            MainApplication.SmokeShoyu.Needcook = 0
            MainApplication.Whey.Needcook = 0

            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("ゼロ化")
                    .setMessage("日付以外の各内部データがゼロになります")
                    .setPositiveButton("OK", { dialog, which ->
                        // TODO:Yesが押された時の挙動
                        val a :Int = 0
                    })
                    .show()

        }

        return returnVal
    }
*/

}