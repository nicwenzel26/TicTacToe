package com.csci448.nwenzel_A2.data

import androidx.room.TypeConverter
import java.util.*

/*
Allows the database and functions pulling from the data base to understand what Java dates are
and what UUIDs are since they are not built into SQLite
 */

class HistoryConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}

