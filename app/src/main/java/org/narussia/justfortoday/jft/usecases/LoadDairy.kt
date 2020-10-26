package org.narussia.justfortoday.jft.usecases

import org.narussia.justfortoday.data.source.dairyRepository.DairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.IDairyRepository

class LoadDairy {

    private val dairyRepository: IDairyRepository = DairyRepository()

    suspend fun loadDairy() {
        dairyRepository.loadDairy()
    }
}
