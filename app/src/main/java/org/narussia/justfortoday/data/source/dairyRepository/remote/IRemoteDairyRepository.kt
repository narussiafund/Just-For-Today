package org.narussia.justfortoday.data.source.dairyRepository.remote

import org.narussia.justfortoday.data.Dairy

interface IRemoteDairyRepository {
    fun getDairy(callback: (Dairy) -> Unit)
}
