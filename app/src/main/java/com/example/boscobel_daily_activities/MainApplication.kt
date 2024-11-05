package com.example.boscobel_daily_activities


import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {
    companion object {
        var mYear:Int = 0
        var mMonth:Int = 0
        var mDay:Int = 0
        var MAXCUSTOMER : Int = 0
        var Soup = Dish1("スープ　　　　　", 0, 0, 0, 0)
        var PotatoSalad = Dish1("ポテサラ　　　　", 0, 0, 0, 0)
        var CarrotRapee = Dish1("人参ラぺ　　　　", 0, 0, 0, 0)
        var Rice = Dish1("ライス　　　　　", 0, 0, 0, 0)
        var CurryRice = Dish1("カレー用ライス　",0,0,0,0)
        var CurryEgg = Dish1("カレー用卵　　　",0,0,0,0)
        var Hamberg = Dish1("ハンバーグ　　　", 0, 0, 0, 0)
        var Locomoko = Dish1("ロコモコ　　　　", 0, 0, 0, 0)
        var Sarmon = Dish1("サーモン燻製　　", 0, 0, 0, 0)
        var Chiken = Dish1("チキングリル　　", 0, 0, 0, 0)
        var RoastBeef = Dish1("ローストビーフ　", 0, 0, 0, 0)
        var Pizza = Dish1("ピザ野菜　　　　",0,0,0,0)
        var VenisonCurry = Dish1("鹿カレー　　　　",0,0,0,0)
        var VenisonSteak = Dish1("鹿ステーキ　　　",0,0,0,0)
        var Gratin = Dish1("サーモンプレート",0,0,0,0) //グラタン
        var WhiteSource = Dish1("ポキ丼　　　　　",0,0,0,0) //ホワイトソース
        var Bread =Dish1("パン　　　　　　",0,0,0,0)
        var R8 = Dish1("白ご飯　　　　　", 0, 0,0,0) //ハムサンド
        var R9 = Dish1("リザーブ　　　　", 0,0,0,0) //煮込みハンバーグ
        var R0 = Dish1("リザーブ　　　　",0,0,0,0)
        var R11 = Dish1("リザーブ　　　　",0,0,0,0)
        var R12 = Dish1("リザーブ　　　　", 0,0,0,0)

        var Mayonnaise = Seasoning1("マヨネーズ　　　　　", 0)
        var Tartar = Seasoning1("タルタル　　　　　　",0)
        var Mayodre = Seasoning1("マヨドレ　　　　　　",0)
        var Soydre = Seasoning1("醤油ドレ　　　　　　", 0)
        var Rapeedre = Seasoning1("ラぺドレ　　　　　　",0)
        var TomatoSauce = Seasoning1("トマトソース　　　　",0)
        var Balsamic = Seasoning1("バルサミコ　　　　　",0)
        var SmokeShoyu = Seasoning1("燻製醤油　　　　　　",0)
        var Whey = Seasoning1("ホエー漬け　　　　　",0)
        var PizzaSauce = Seasoning1("ピザソース　　　　　", 0)
        var Raisin = Seasoning1("レーズン　　　　　　", 0)
        var Blueberry = Seasoning1("ブルーベリーソース　", 0)
        var RR4 = Seasoning1("生クリーム　　　　　",0)
        var RR5 = Seasoning1("レリッシュ　　　　　",0) //サンド用ドレッシング
        var RR6 = Seasoning1("パッションフルーツ　",0)
        var RR7 = Seasoning1("リザーブ　　　　　　",0)
        var RR8 = Seasoning1("リザーブ　　　　　　",0)
        var RR9 = Seasoning1("リザーブ　　　　　　",0)

    }

    override fun onCreate() {
        super.onCreate()

        //初期化
        Realm.init(this)
        //設定

        /*        val builder = RealmConfiguration.Builder()
                        .schemaVersion(1L)
                        .migration(MyMigration())
                        .build()
                Realm.setDefaultConfiguration(builder)

         */





        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            // .readOnly()
            .build()

        Realm.setDefaultConfiguration(config)





    }
}