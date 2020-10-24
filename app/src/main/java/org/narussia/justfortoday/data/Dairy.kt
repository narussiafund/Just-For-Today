package org.narussia.justfortoday.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Dairy {
    @PrimaryKey @ColumnInfo(name = "date") var date: String = "01.01"
    @ColumnInfo(name = "day") var  day = 0
    @ColumnInfo(name = "month") var  month = 0
    @ColumnInfo(name = "title") var  title: String = ""
    @ColumnInfo(name = "basetext") var  basetext: String = ""
    @ColumnInfo(name = "daytext") var  daytext: String = ""
    @ColumnInfo(name = "justfortoday") var  justfortoday: String = ""
}
