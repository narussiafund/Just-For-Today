package org.narussia.justfortoday.jft

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.jft.usecases.GetDairy

class JFTViewModel : ViewModel() {

    private val dairyLiveData = MutableLiveData<Dairy>()
    private val getDairyCase = GetDairy()

    fun getDairy(): LiveData<Dairy> {
        return dairyLiveData
    }

    fun loadDairy() {
        getDairyCase.getDairy { dairy -> dairyLiveData.postValue(dairy) }
    }
}
