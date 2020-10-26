package org.narussia.justfortoday.data.source.dairyRepository.remote

import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.utils.ResultRetrofit

interface IRemoteDairyRepository {
    suspend fun getDairy(): ResultRetrofit<Dairy>
}
