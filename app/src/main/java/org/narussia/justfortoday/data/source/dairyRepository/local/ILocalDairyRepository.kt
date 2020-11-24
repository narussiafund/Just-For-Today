package org.narussia.justfortoday.data.source.dairyRepository.local

import androidx.lifecycle.LiveData
import org.narussia.justfortoday.data.Dairy

interface ILocalDairyRepository {
    fun getDairy(currentDay: String): LiveData<Dairy>
    fun updateDairy(dairy: Dairy)
}
