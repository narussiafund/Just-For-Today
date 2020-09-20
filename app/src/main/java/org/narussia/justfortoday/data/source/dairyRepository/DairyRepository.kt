package org.narussia.justfortoday.data.source.dairyRepository

import com.google.gson.Gson
import org.narussia.justfortoday.data.Dairy
import java.net.URL
import java.util.*

class DairyRepository : IDairyRepository {

    private val dairyUrl = "https://na-russia.org/egednevnik/dairy_json.php"
    private val cal = Calendar.getInstance()

    override fun getDairy(): Dairy {

        val gson = Gson()

        val thread = Thread {
            try {
                val dairyJSON = URL(getDairyUrl()).readText()
                val dairy: Dairy = gson.fromJson(dairyJSON, Dairy::class.java)
            } catch (e: Exception) {
                println("Exception: $e")
            }
        }
        thread.start()
        return Dairy()
    }

    fun getDairyUrl(): String? {
        return (dairyUrl + "?c=android&d=" + cal.get(Calendar.DAY_OF_MONTH)
                + "&m=" + (cal.get(Calendar.MONTH) + 1))
    }
}

