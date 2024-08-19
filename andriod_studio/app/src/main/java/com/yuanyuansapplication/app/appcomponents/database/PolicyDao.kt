//可用代码
//package com.example.green.database
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.example.green.models.PrivacyPolicy
//
//@Dao
//interface PolicyDao {
//
//    @Insert
//    suspend fun insertAll(vararg policy: PrivacyPolicy)
//    @Query("DELETE FROM policies")
//    suspend fun deleteAll()
//    @Query("SELECT * FROM policies")
//    fun getAllPolicies(): LiveData<List<PrivacyPolicy>>
//    @Query("SELECT * FROM policies WHERE id = :id LIMIT 1")
//    fun getPolicyById(id: Int): LiveData<PrivacyPolicy>
//
//}
//package com.example.green.database
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.green.models.PrivacyPolicy
//
//@Dao
//interface PolicyDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg policies: PrivacyPolicy)
//
//    @Query("DELETE FROM policies")
//    suspend fun deleteAll()
//
//    @Query("SELECT * FROM policies")
//    fun getAllPolicies(): LiveData<List<PrivacyPolicy>>
//
//    @Query("SELECT * FROM policies WHERE id = :id LIMIT 1")
//    fun getPolicyById(id: Int): LiveData<PrivacyPolicy>
//}


package com.yuanyuansapplication.app.appcomponents.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyuansapplication.app.modules.detailedreport.data.model.PrivacyPolicy

@Dao
interface PolicyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg policies: PrivacyPolicy)

    @Query("DELETE FROM policies")
    suspend fun deleteAll()

    @Query("SELECT * FROM policies")
    fun getAllPolicies(): LiveData<List<PrivacyPolicy>>

    @Query("SELECT * FROM policies WHERE id = :id LIMIT 1")
    fun getPolicyById(id: Int): LiveData<PrivacyPolicy>
}
