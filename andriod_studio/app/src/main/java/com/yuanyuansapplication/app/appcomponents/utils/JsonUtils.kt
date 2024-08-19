package com.yuanyuansapplication.app.appcomponents.utils
//
//import android.content.Context
//import android.util.Log
import com.yuanyuansapplication.app.modules.detailedreport.data.model.PrivacyPolicy
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Regulation
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//object JsonUtils {
//    fun loadRegulations(context: Context): List<Regulation>? {
//        return try {
//            val jsonString = context.assets.open("regulations.json").bufferedReader().use { it.readText() }
//            val listType = object : TypeToken<List<Regulation>>() {}.type
//            val regulations: List<Regulation> = Gson().fromJson(jsonString, listType)
//            Log.d("JsonUtils", "Loaded regulations from JSON: $regulations")
//            regulations
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.e("JsonUtils", "Error loading regulations", e)
//            null
//        }
//    }
//
//    fun loadPrivacyPolicy(context: Context): List<PrivacyPolicy>? {
//        return try {
//            val jsonString = context.assets.open("policy.json").bufferedReader().use { it.readText() }
//            val listType = object : TypeToken<List<PrivacyPolicy>>() {}.type
//            val policies: List<PrivacyPolicy> = Gson().fromJson(jsonString, listType)
//            Log.d("JsonUtils", "Loaded privacy policies from JSON: $policies")
//            policies
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.e("JsonUtils", "Error loading privacy policies", e)
//            null
//        }
//    }
//}

// JsonUtils.kt
import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import com.google.gson.reflect.TypeToken
object JsonUtils {

    private fun loadJSONFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadRegulations(context: Context): List<Regulation>? {
        val json = loadJSONFromAsset(context, "regulations.json")
        return json?.let { parseRegulationJSON(it) }
    }

    fun loadPrivacyPolicy(context: Context): List<PrivacyPolicy>? {
        val json = loadJSONFromAsset(context, "policy.json")
        return json?.let { parsePolicyJSON(it) }
    }

    private fun parseRegulationJSON(jsonString: String): List<Regulation> {
        val gson = Gson()
        val type = object : TypeToken<List<Regulation>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    private fun parsePolicyJSON(jsonString: String): List<PrivacyPolicy> {
        val gson = Gson()
        val type = object : TypeToken<List<PrivacyPolicy>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}
