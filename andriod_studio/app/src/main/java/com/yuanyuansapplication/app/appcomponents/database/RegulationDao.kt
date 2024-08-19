//可用代码
//package com.example.green.database
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.OnConflictStrategy
//import com.example.green.models.Regulation
//import com.example.green.models.PrivacyPolicy
//
//@Dao
//interface RegulationDao {
//
//    @Insert
//    suspend fun insertAll(vararg regulations: Regulation)
//
//
////    @Insert
////    fun insertAll(regulations: List<Regulation>)
////
////    @Query("SELECT * FROM regulations")
////    fun getAllRegulations(id: Int): LiveData<List<Regulation>>
////    @Query("SELECT COUNT(*) FROM regulations")
////    suspend fun getCount(): Int
//    @Query("DELETE FROM regulations")
//    suspend fun deleteAll()
//    @Query("SELECT * FROM regulations")
//    fun getAllRegulations(): LiveData<List<Regulation>>
//
//    @Query("SELECT * FROM regulations WHERE id = :id")
//    fun getRegulationById(id: Int): LiveData<Regulation>
//
//}
//

package com.yuanyuansapplication.app.appcomponents.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Regulation
@Dao
interface RegulationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg regulations: Regulation)

    @Query("DELETE FROM regulations")
    suspend fun deleteAll()

    @Query("SELECT * FROM regulations")
    fun getAllRegulations(): LiveData<List<Regulation>>

    @Query("SELECT * FROM regulations WHERE name = :name LIMIT 1")
    fun getRegulationById(name: String): LiveData<Regulation>
}

