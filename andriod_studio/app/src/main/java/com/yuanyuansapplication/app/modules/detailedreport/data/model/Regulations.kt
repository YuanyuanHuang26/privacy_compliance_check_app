package com.yuanyuansapplication.app.modules.detailedreport.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regulations")
//@TypeConverters(Converters::class)
data class Regulation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val questions: List<Question>
)

data class Question(
    val questionId: Int,
    val questionText: String
)
