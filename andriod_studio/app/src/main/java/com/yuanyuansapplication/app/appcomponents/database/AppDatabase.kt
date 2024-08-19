//package com.example.green.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.example.green.models.PrivacyPolicy
//import com.example.green.models.Regulation
//
//@Database(entities = [Regulation::class, PrivacyPolicy::class], version = 1)
//@TypeConverters(Converters::class)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun regulationDao(): RegulationDao
//    abstract fun policyDao(): PolicyDao
//
//    companion object {
//        @Volatile private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
package com.yuanyuansapplication.app.appcomponents.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yuanyuansapplication.app.modules.detailedreport.data.model.PrivacyPolicy
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Regulation
@Database(entities = [Regulation::class, PrivacyPolicy::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun regulationDao(): RegulationDao
    abstract fun policyDao(): PolicyDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
