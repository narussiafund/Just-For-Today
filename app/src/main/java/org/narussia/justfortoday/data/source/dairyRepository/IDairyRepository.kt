package org.narussia.justfortoday.data.source.dairyRepository

import androidx.lifecycle.LiveData
import org.narussia.justfortoday.data.Dairy

interface IDairyRepository {
    fun getDairy(): LiveData<Dairy>
    suspend fun loadDairy()
}
