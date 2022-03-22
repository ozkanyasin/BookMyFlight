package com.example.bookmyflight.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FlightDB(
    @field:PrimaryKey(autoGenerate = true)
    var uid: Int,

    @field:ColumnInfo(name = "plant_name")
    var plantName: String,

    @field:ColumnInfo(name = "start_date")
    var startDate: String,

    @field:ColumnInfo(name = "start_time")
    var startTime: String,

    @field:ColumnInfo(name = "routine")
    var routine: String,

    @field:ColumnInfo(name = "routineId")
    var routineId: Int,

    @field:ColumnInfo(name = "photo_url")
    var photoUrl: String
)
