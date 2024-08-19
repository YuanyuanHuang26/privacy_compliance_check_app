package com.yuanyuansapplication.app.modules.detailedreport.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yuanyuansapplication.app.appcomponents.database.Converters

@Entity(tableName = "policies")
@TypeConverters(Converters::class)
data class PrivacyPolicy(
    @PrimaryKey val id: Int,
    val name: String,
    val details: List<Detail>
)
data class Detail(
    val id: Int,
    val text: String
)
