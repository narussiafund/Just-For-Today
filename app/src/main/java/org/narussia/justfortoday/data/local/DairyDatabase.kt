package org.narussia.justfortoday.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.narussia.justfortoday.data.Dairy

@Database(entities = [Dairy::class], version = 2)
abstract class DairyDatabase : RoomDatabase() {

    abstract fun dairyDao(): DairyDao

    companion object {

        @Volatile
        private var INSTANCE: DairyDatabase? = null

        fun getDairyDatabase(context: Context): DairyDatabase =
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, DairyDatabase::class.java, "database").build().also { INSTANCE = it }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
