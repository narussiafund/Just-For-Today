package org.narussia.justfortoday.network

import org.narussia.justfortoday.data.Dairy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IDairyAPI {

    @GET(DAIRY_JSON)
    fun requestDairy(
        @Query(PLATFORM) platform: String,
        @Query(DAY) dayOfMonth: Int,
        @Query(MONTH) month: Int,
    ): Call<Dairy>

    companion object {
        private const val DAIRY_JSON = "dairy_json.php"
        private const val PLATFORM = "c"
        private const val DAY = "d"
        private const val MONTH = "m"
    }
}
