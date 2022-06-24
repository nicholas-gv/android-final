package com.example.apps.android.davaleba8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "datarecords")
data class DataRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val value: String,
)

