package org.narussia.justfortoday.data.source.dairyRepository

import org.narussia.justfortoday.data.Dairy

interface IDairyRepository {
    fun getDairy() : Dairy
}
