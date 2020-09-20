package org.narussia.justfortoday.jft.usecases

import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.data.source.dairyRepository.DairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.IDairyRepository
class GetDairy {

    private val dairyRepository: IDairyRepository = DairyRepository()
    fun getDairy(callback: (Dairy) -> Unit) {

        callback(dairyRepository.getDairy())
    }





}
