package org.narussia.justfortoday.jft.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.data.source.dairyRepository.DairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.IDairyRepository

class GetDairy {

    private val dairyRepository: IDairyRepository = DairyRepository()
    private val dairyMediatorLiveData: MediatorLiveData<Dairy> = MediatorLiveData<Dairy>()

    fun getDairy(loadDairy: () -> Unit): LiveData<Dairy> {
        val dairyLiveData = dairyRepository.getDairy()
        dairyMediatorLiveData.addSource(dairyLiveData) { dairy ->
            if (dairy == null) {
                loadDairy()
            } else {
                dairyMediatorLiveData.removeSource(dairyLiveData)
                dairyMediatorLiveData.setValue(dairy)
            }
        }
        return dairyMediatorLiveData
    }
}
