//package com.example.green.database
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.example.green.models.Question
//
//class Converters {
//    private val gson = Gson()
//
//    @TypeConverter
//    fun fromQuestionListToJson(questions: List<Question>): String = gson.toJson(questions)
//
//    @TypeConverter
//    fun fromJsonToQuestionList(json: String): List<Question> {
//        val type = object : TypeToken<List<Question>>() {}.type
//        return gson.fromJson(json, type)
//    }
//}
//可以运行的
//package com.example.green.database
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.example.green.models.Detail
//import com.example.green.models.Question
//
//class Converters {
//    private val gson = Gson()
//
//    @TypeConverter
//    fun fromDetailListToJson(details: List<Detail>?): String = gson.toJson(details)
//
//    @TypeConverter
//    fun fromJsonToDetailList(json: String?): List<Detail> {
//        if (json == null || json.isEmpty()) return emptyList()
//        val type = object : TypeToken<List<Detail>>() {}.type
//        return gson.fromJson(json, type)
//    }
//
//    @TypeConverter
//    fun fromQuestionListToJson(questions: List<Question>?): String = gson.toJson(questions)
//
//    @TypeConverter
//    fun fromJsonToQuestionList(json: String?): List<Question> {
//        if (json == null || json.isEmpty()) return emptyList()
//        val type = object : TypeToken<List<Question>>() {}.type
//        return gson.fromJson(json, type)
//    }
//}


package com.yuanyuansapplication.app.appcomponents.database

import androidx.room.TypeConverter
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Detail
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromDetailListToJson(value: List<Detail>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Detail>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToDetailList(value: String): List<Detail> {
        val gson = Gson()
        val type = object : TypeToken<List<Detail>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromQuestionListToJson(value: List<Question>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToQuestionList(value: String): List<Question> {
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() {}.type
        return gson.fromJson(value, type)
    }
}
