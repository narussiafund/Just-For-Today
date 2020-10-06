package org.narussia.justfortoday.data.source.dairyRepository.remote

import android.util.Log
import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.network.IDairyAPI
import org.narussia.justfortoday.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class RemoteDairyRepository : IRemoteDairyRepository {

    private val networkService by lazy { NetworkService.instance.buildService(IDairyAPI::class.java) }

    override fun getDairy(callback: (Dairy) -> Unit) {
        val cal = Calendar.getInstance()
        networkService.requestDairy(
            platform = PLATFORM,
            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH),
            month = cal.get(Calendar.MONTH) + 1
        ).enqueue(object : Callback<Dairy> {
            override fun onResponse(call: Call<Dairy>, response: Response<Dairy>) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<Dairy>, t: Throwable) {
                Log.e("Error get dairy", "Error: ${t.message}")
            }
        })
    }

    companion object {
        private const val PLATFORM = "android"
    }
}
