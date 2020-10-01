package org.narussia.justfortoday.network

import org.narussia.justfortoday.data.Dairy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface IDairyRetrofitInterface {
    val cal: Calendar
        get() = Calendar.getInstance()

    @GET("dairy_json.php")
    fun requestDairy(
        @Query("c") platformString: String = "android",
        @Query("d") dateDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        @Query("m") dateMonth: Int = cal.get(Calendar.MONTH) + 1,
    ): Call<Dairy>
}


