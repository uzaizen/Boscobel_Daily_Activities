package com.example.boscobel_daily_activities

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import java.util.*

open class CookingItem:RealmObject(), Serializable {

    @PrimaryKey
    var id: Int =0

    var Ryear: Int = 0
    var Rmonth: Int = 0
    var Rday:Int = 0

    var RMAXCUSTOMER : Int = 0

    var SoupRequired:Int = 0
    var SoupStock:Int = 0
    var SoupCook:Int = 0
    var SoupTotal:Int = 0

    var PotatoSaladRequired:Int = 0
    var PotatoSaladStock:Int = 0
    var PotatoSaladCook:Int = 0
    var PotatoSaladTotal:Int = 0

    var CarrotRapeeRequired:Int = 0
    var CarrotRapeeStock:Int = 0
    var CarrotRapeeCook:Int = 0
    var CarrotRapeeTotal:Int = 0

    var RiceRequired:Int = 0
    var RiceStock:Int = 0
    var RiceCook:Int = 0
    var RiceTotal:Int = 0

    var HambergRequired:Int = 0
    var HambergStock:Int = 0
    var HambergCook:Int = 0
    var HambergTotal:Int = 0

    var LocomokoRequired:Int = 0
    var LocomokoStock:Int = 0
    var LocomokoCook:Int = 0
    var LocomokTotal:Int = 0

    var SarmonRequired:Int = 0
    var SarmonStock:Int = 0
    var SarmonCook:Int = 0
    var SarmonTotal:Int = 0

    var ChikenRequired:Int = 0
    var ChikenStock:Int = 0
    var ChikenCook:Int = 0
    var ChikenTotal:Int = 0

    var RoastBeefRequired:Int = 0
    var RoastBeefStock:Int = 0
    var RoastBeefCook:Int = 0
    var RoastBeefTotal:Int = 0

    var PizzaRequired:Int = 0
    var PizzaStock:Int = 0
    var PizzaCook:Int = 0
    var PizzaTotal:Int = 0

    var reserve1Required:Int = 0  //For Venison Curry
    var reserve1Stock:Int = 0
    var reserve1Cook:Int = 0
    var reserve1Total:Int = 0

    var reserve2Required:Int = 0  //For Venison Steak
    var reserve2Stock:Int = 0
    var reserve2Cook:Int = 0
    var reserve2Total:Int = 0

    var reserve3Required:Int = 0  // For Rice of Curry
    var reserve3Stock:Int = 0
    var reserve3Cook:Int = 0
    var reserve3Total:Int = 0

    var reserve4Required:Int = 0  //For Egg of Curry
    var reserve4Stock:Int = 0
    var reserve4Cook:Int = 0
    var reserve4Total:Int = 0

    var reserve5Required:Int = 0  //For グラタン
    var reserve5Stock:Int = 0
    var reserve5Cook:Int = 0
    var reserve5Total:Int = 0

    var reserve6Required:Int = 0 //For white source
    var reserve6Stock:Int = 0
    var reserve6Cook:Int = 0
    var reserve6Total:Int = 0

    var reserve7Required:Int = 0 //For bread
    var reserve7Stock:Int = 0
    var reserve7Cook:Int = 0
    var reserve7Total:Int = 0

    var reserve8Required:Int = 0
    var reserve8Stock:Int = 0
    var reserve8Cook:Int = 0
    var reserve8Total = 0

    var reserve9Required:Int = 0
    var reserve9Stock:Int = 0
    var reserve9Cook:Int = 0
    var reserve9Total:Int = 0

    var reserve0Required:Int = 0
    var reserve0Stock:Int = 0
    var reserve0Cook:Int = 0
    var reserve0Total:Int = 0

    var reserve11Required:Int = 0
    var reserve11Stock:Int = 0
    var reserve11Cook:Int = 0
    var reserve11Total:Int = 0

    var reserve12Required:Int = 0
    var reserve12Stock:Int = 0
    var reserve12Cook:Int = 0
    var reserve12Total:Int = 0


    var MayonnaiseNeedCook:Int = 0
    var TartarNeedCook:Int = 0
    var MayodreNeedCook:Int = 0
    var SoydreNeedCCook:Int = 0
    var RapeedreNeedCook:Int = 0
    var TomatoSauceNeedCook:Int = 0
    var BalsamicNeedCook:Int = 0
    var SmokeShoyuNeedCook:Int = 0
    var WheyNeedCook:Int = 0
    var Reserve1NeedCook:Int = 0   //Pizza Sauce
    var Reserve2NeedCook:Int = 0   //Raisin
    var Reserve3NeedCook:Int = 0   //Blueberry sauce
    var Reserve4NeedCook:Int = 0
    var Reserve5NeedCook:Int = 0
    var Reserve6NeedCook:Int = 0
    var Reserve7NeedCook:Int = 0
    var Reserve8NeedCoook:Int = 0
    var Reserve9NeedCook:Int = 0

}
