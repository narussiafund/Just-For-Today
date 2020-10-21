package org.narussia.justfortoday.data.local

import androidx.room.*
import org.narussia.justfortoday.data.Dairy

@Dao
interface DairyDao {

    @Insert
    fun insertDairy(Dairy: Dairy)

    @Update
    fun updateDairy(Dairy: Dairy)

    @Delete
    fun deleteDairy(Dairy: Dairy)

    @Query("SELECT * FROM Dairy WHERE date == :date")
    fun getDairyByDate(date: String): Dairy

}