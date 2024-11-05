package com.example.boscobel_daily_activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.boscobel_daily_activities.MainApplication.Companion.Balsamic
import com.example.boscobel_daily_activities.MainApplication.Companion.Blueberry
import com.example.boscobel_daily_activities.MainApplication.Companion.CarrotRapee
import com.example.boscobel_daily_activities.MainApplication.Companion.Chiken
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryEgg
import com.example.boscobel_daily_activities.MainApplication.Companion.CurryRice
import com.example.boscobel_daily_activities.MainApplication.Companion.Hamberg
import com.example.boscobel_daily_activities.MainApplication.Companion.Locomoko
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayodre
import com.example.boscobel_daily_activities.MainApplication.Companion.Mayonnaise
import com.example.boscobel_daily_activities.MainApplication.Companion.Pizza
import com.example.boscobel_daily_activities.MainApplication.Companion.PizzaSauce
import com.example.boscobel_daily_activities.MainApplication.Companion.PotatoSalad
import com.example.boscobel_daily_activities.MainApplication.Companion.R8
import com.example.boscobel_daily_activities.MainApplication.Companion.RR4
import com.example.boscobel_daily_activities.MainApplication.Companion.RR5
import com.example.boscobel_daily_activities.MainApplication.Companion.RR6
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
import com.example.boscobel_daily_activities.MainApplication.Companion.Whey
//import java.lang.NullPointerException
import kotlin.math.roundToInt

//各プロセスの完了、未完了を設定

class StatusCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_check)

        val Done: String = "完了"
        val NotDone: String = "未完了"
        var returnStatus: String

        val processName = intent.getStringExtra("processName")
        val processStatus = intent.getStringExtra("processStatus")
        val position = intent.getIntExtra("position",0)
        var ProcessStatus:String = processStatus.toString()

        val processNameText = findViewById<TextView>(R.id.ProcessNameText)
        val processStatusButton = findViewById<Button>(R.id.ProcessStatusButton)
        val processdetail = findViewById<TextView>(R.id.ProcessDetail)
        val backButton = findViewById<Button>(R.id.BackButton)
        var dressing : String = ""
        var smoking : String = ""
        var eggi: Int = 0
        var eggj: Int = 0
        var eggs: String = ""

        processNameText.text = processName
        processStatusButton.text = processStatus

        if (Mayonnaise.Needcook == 1){dressing = dressing + "マヨネーズ "}
        if (Tartar.Needcook == 1){dressing = dressing + "タルタル "}
        if (Mayodre.Needcook == 1){dressing = dressing +"マヨドレ "}
        if (Rapeedre.Needcook == 1){dressing = dressing + "ラぺドレ "}
        if (TomatoSauce.Needcook == 1) {dressing = dressing + "トマトソース "}
        if (Balsamic.Needcook == 1) {dressing = dressing + "バルサミコ "}
        if (Soydre.Needcook == 1){dressing = dressing + "醤油ドレ "}
        if (Whey.Needcook == 1){dressing = dressing + "ホエー漬け "}
        if (PizzaSauce.Needcook == 1) {dressing = dressing + "ピザソース "}
        if (Raisin.Needcook == 1) {dressing = dressing + "レーズン "}
        if (Blueberry.Needcook == 1) {dressing = dressing + "ブルーベリーソース　"}
        if (RR4.Needcook == 1) {dressing = dressing + "生クリーム　"}
        if (RR5.Needcook == 1) {dressing = dressing + "レリッシュ　"}
        if (RR6.Needcook == 1) {dressing = dressing + "パッションフルーツ"}

        if (Hamberg.Cook != 0 || Locomoko.Cook !=0 ){smoking = smoking + "ハンバーグ "}
        if (Sarmon.Cook !=0 ){smoking = smoking + "サーモン "}
        if (SmokeShoyu.Needcook !=0 ){smoking = smoking + "燻製醤油"}

        if (PotatoSalad.Cook != 0) {
            val PSCD: Double = (PotatoSalad.Cook/3*2).toDouble() / 250
            val PSCE2: Double = Math.ceil(PSCD)
            eggi = PSCE2.toInt()
        }
        if (Tartar.Needcook == 1){
            eggj = 2
        }
        val eggk: Int = CurryEgg.Cook

        val potatoSaladCook = PotatoSalad.Cook/3*2
        val mayonnaiseWeight = (potatoSaladCook / 4.2).roundToInt()
        val saltTeaspoon = (potatoSaladCook / 500.0)

        when (processName){
            "解凍処理" ->  processdetail.text = "パン、ハンバーグ(${Hamberg.Cook})、ロコモコ(${Locomoko.Cook})、サーモン(${Sarmon.Cook})、チキン(${Chiken.Cook})、ローストビーフ(${RoastBeef.Cook})、ハム、ベーコン"
            "ジャガイモ、卵をゆでる" ->  processdetail.text = "ジャガイモ(${potatoSaladCook}g) 卵 ポテサラ(${eggi}) タルタル(${eggj})　カレー(${eggk})"
            "炊飯器セット" ->  processdetail.text = "古代米(${Rice.Cook}合　弥生紫${Rice.Cook*10}g 米${Rice.Cook*140}g トータル${Rice.Cook*360}g)\n" +
                    "白米(${R8.Cook}合 米${R8.Cook*150}g トータル${R8.Cook*360}g)\n" +
                    " カレー用(${CurryRice.Cook}合　米${CurryRice.Cook*150}g トータル${CurryRice.Cook*360}g) "
            "野菜類処理準備" -> processdetail.text = "ラぺ人参(${CarrotRapee.Cook})、パプリカ、ブロッコリー(${Hamberg.Total})、スープ野菜、ピザ野菜(${Pizza.Cook})、ホエー漬け"
            "燻製開始" -> processdetail.text = smoking
            "ドレッシング類準備" -> processdetail.text = dressing
            "スープ準備" -> processdetail.text = "スープ (${Soup.Cook})"
            "ポテサラ仕上げ" -> processdetail.text = "マヨネーズ(${mayonnaiseWeight}g) 塩(小さじ${String.format("%.1f", saltTeaspoon)})"      }

        processStatusButton.setOnClickListener {
            if (ProcessStatus == NotDone) {
                ProcessStatus = Done
                processStatusButton.text = ProcessStatus
            } else {
                ProcessStatus = NotDone
                processStatusButton.text = ProcessStatus
            }
        }

        backButton.setOnClickListener {
//            val intent = Intent()
            intent.putExtra("returnstatus", ProcessStatus)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}
