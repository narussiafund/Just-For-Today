package org.narussia.justfortoday.data.source.dairyRepository

import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.data.source.dairyRepository.remote.IRemoteDairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.remote.RemoteDairyRepository

class DairyRepository : IDairyRepository {

    private val remoteDairyRepository: IRemoteDairyRepository by lazy { RemoteDairyRepository() }

    override fun getDairy(callback: (Dairy) -> Unit) {
        remoteDairyRepository.getDairy(callback)
    }
}
