package org.narussia.justfortoday.data.source.dairyRepository.remote

import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.network.IDairyAPI
import org.narussia.justfortoday.network.NetworkService
import org.narussia.justfortoday.utils.ResultRetrofit
import org.narussia.justfortoday.utils.awaitResult
import java.util.Calendar

class RemoteDairyRepository : IRemoteDairyRepository {

    private val networkService by lazy { NetworkService.instance.buildService(IDairyAPI::class.java) }

    override suspend fun getDairy(): ResultRetrofit<Dairy> {
        val cal = Calendar.getInstance()
        return networkService.requestDairy(
            platform = PLATFORM,
            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH),
            month = cal.get(Calendar.MONTH) + 1
        ).awaitResult()
    }

    companion object {
        private const val PLATFORM = "android"
    }
}
