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

        fun getDairyDatabase(context: Context): DairyDatabase? {
            if (INSTANCE == null) {
                synchronized(DairyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DairyDatabase::class.java, "database").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
