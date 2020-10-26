package org.narussia.justfortoday.jft

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.jft.usecases.GetDairy
import org.narussia.justfortoday.jft.usecases.LoadDairy

class JFTViewModel : ViewModel() {

    private val getDairyCase = GetDairy()
    private val loadDairyCase = LoadDairy()

    fun getDairy(): LiveData<Dairy> = getDairyCase.getDairy {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadDairyCase.loadDairy()
            }
        }
    }
}
