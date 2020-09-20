package org.narussia.justfortoday.data.source.dairyRepository

import com.google.gson.Gson
import org.narussia.justfortoday.data.Dairy
import java.net.URL
import java.util.*

class DairyRepository : IDairyRepository {

    override fun getDairy(callback: (Dairy) -> Unit) {
        val thread = Thread {
            try {
                callback(Gson().fromJson(URL(getDairyUrl()).readText(), Dairy::class.java))
            } catch (e: Exception) {
                println("Exception: $e")
            }
        }
        thread.start()
    }

    private fun getDairyUrl(): String {
        val cal = Calendar.getInstance()
        return "$DAIRY_URL?c=android&d=${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}&m=${cal.get(Calendar.MONTH) + 1}"
    }

    companion object {
        const val DAIRY_URL = "https://na-russia.org/egednevnik/dairy_json.php"
    }
}
