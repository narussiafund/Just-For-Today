package org.narussia.justfortoday.jft.usecases

import org.narussia.justfortoday.data.Dairy
import org.narussia.justfortoday.data.source.dairyRepository.DairyRepository
import org.narussia.justfortoday.data.source.dairyRepository.IDairyRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GetDairy {

    private val dairyRepository: IDairyRepository = DairyRepository()
    private val locale = Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY)

    fun getDairy(callback: (Dairy) -> Unit) {
        Thread {
            dairyRepository.getDairy { dairy ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, dairy.day)
                    set(Calendar.MONTH, dairy.month)
                }
                dairy.date = SimpleDateFormat(DATE_FORMAT, locale).format(calendar.time)
                callback(dairy)
            }
        }.start()
    }

    companion object {
        private const val LOCALE_LANGUAGE = "ru"
        private const val LOCALE_COUNTRY = "RU"
        private const val DATE_FORMAT = "d MMMM, EEEE"
    }
}
