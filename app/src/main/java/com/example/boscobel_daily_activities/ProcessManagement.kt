package com.example.boscobel_daily_activities

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import java.util.*

open class ProcessManagement: RealmObject(), Serializable {
    @PrimaryKey
    var id: Long = 0

    var Ryear: Int = 0
    var Rmonth: Int = 0
    var Rday:Int = 0

    var item0:String = ""
    var status0:String = ""
    var item1: String = ""
    var status1: String =""
    var item2: String =""
    var status2: String =""
    var item3: String =""
    var status3: String =""
    var item4: String =""
    var status4: String =""
    var item5: String =""
    var status5: String =""
    var item6: String =""
    var status6: String =""
    var item7: String =""
    var status7: String =""
    var item8: String =""
    var status8: String =""
    var item9: String =""
    var status9: String =""
    var item10: String =""
    var status10: String =""
    var item11: String =""
    var status11: String =""
    var item12: String =""
    var status12: String =""
    var item13: String =""
    var status13: String =""
    var item14: String =""
    var status14: String =""
    var item15: String =""
    var status15: String =""
    var item16: String =""
    var status16: String =""
    var item17: String =""
    var status17: String =""
    var item18: String =""
    var status18: String =""
    var item19: String =""
    var status19: String =""
    var item20: String =""
    var status20: String =""
    var item21: String =""
    var status21: String =""
    var item22: String =""
    var status22: String =""
    var item23: String = ""
    var status23: String =""
    var item24: String =""
    var status24:String =""
    var item25:String =""
    var status25:String = ""
    var item26:String=""
    var status26:String =""
    var item27:String =""
    var status27 =""
}