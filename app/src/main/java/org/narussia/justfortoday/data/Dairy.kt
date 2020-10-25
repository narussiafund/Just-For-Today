package org.narussia.justfortoday.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dairy(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "day")
    var day: Int,
    @ColumnInfo(name = "month")
    var month: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "basetext")
    var basetext: String,
    @ColumnInfo(name = "daytext")
    var daytext: String,
    @ColumnInfo(name = "justfortoday")
    var justfortoday: String
)
