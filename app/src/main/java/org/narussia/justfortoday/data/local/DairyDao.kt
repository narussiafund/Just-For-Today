package org.narussia.justfortoday.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.narussia.justfortoday.data.Dairy

@Dao
interface DairyDao {

    @Delete
    fun deleteDairy(Dairy: Dairy)

    @Insert
    fun insertDairy(Dairy: Dairy)

    @Update
    fun updateDairy(Dairy: Dairy)

    @Query("SELECT * FROM Dairy WHERE date == :date")
    fun getDairyByDate(date: String): Dairy
}
