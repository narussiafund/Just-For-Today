package org.narussia.justfortoday

import android.app.Application
import org.narussia.justfortoday.data.local.DairyDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = DairyDatabase.getDairyDatabase(this)
    }

    companion object {
        lateinit var db: DairyDatabase
    }
}