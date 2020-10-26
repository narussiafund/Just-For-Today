package org.narussia.justfortoday.data.source.dairyRepository.local

import androidx.lifecycle.LiveData
import org.narussia.justfortoday.App
import org.narussia.justfortoday.data.Dairy

class LocalDairyRepository : ILocalDairyRepository {

    override fun getDairy(currentDay: String): LiveData<Dairy> = App.db.dairyDao().getDairyByDate(currentDay)

    override fun updateDairy(dairy: Dairy) {
        App.db.dairyDao().insertDairy(dairy)
    }
}
