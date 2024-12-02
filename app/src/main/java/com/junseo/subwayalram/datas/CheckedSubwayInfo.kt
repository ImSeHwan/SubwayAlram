package com.junseo.subwayalram.datas

import com.junseo.subwayalram.databaseutils.SubwayStatioinDetailInfo
import com.junseo.subwayalram.databaseutils.SubwayStation
import java.util.Calendar

data class CheckedSubwayInfo (
    var subwayStatioinList: List<String>,
    var checkedTime: Calendar
)
