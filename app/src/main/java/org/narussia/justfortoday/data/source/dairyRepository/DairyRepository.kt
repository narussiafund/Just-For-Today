package org.narussia.justfortoday.data.source.dairyRepository

import androidx.lifecycle.LiveData
import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.data.source.dairyRepository.local.ILocalDairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.local.LocalDairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.remote.IRemoteDairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.remote.RemoteDairyRepository
import org.narussia.justfortoday.utils.ResultRetrofit
import timber.log.Timber
import java.util.Calendar

class DairyRepository : IDairyRepository {

    private val remoteDairyRepository: IRemoteDairyRepository by lazy { RemoteDairyRepository() }
    private val localDairyRepository: ILocalDairyRepository by lazy { LocalDairyRepository() }

    override fun getDairy(): LiveData<Dairy> {
        val calendar = Calendar.getInstance()
        return localDairyRepository.getDairy("${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH) + 1}")
    }

    override suspend fun loadDairy() {
        when (val result = remoteDairyRepository.getDairy()) {
            is ResultRetrofit.Ok -> localDairyRepository.updateDairy(result.value)
            is ResultRetrofit.Error -> Timber.e(result.exception)
            is ResultRetrofit.Exception -> Timber.e(result.exception)
        }
    }
}
