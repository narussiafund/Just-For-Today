package org.narussia.justfortoday.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Dairy {
    @PrimaryKey
    var date: String = ""
    var day = 0
    var month = 0
    var title: String = ""
    var basetext: String = ""
    var daytext: String = ""
    var justfortoday: String = ""
}
