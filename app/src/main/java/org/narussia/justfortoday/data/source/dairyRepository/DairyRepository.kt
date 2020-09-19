package org.narussia.justfortoday.data.source.dairyRepository

import org.narussia.justfortoday.data.Dairy

class DairyRepository : IDairyRepository {
    override fun getDairy(): Dairy {
        return Dairy()
    }
}
