package cn.mobiledaily.domain

import java.util.Date
import org.springframework.data.annotation.Transient
import cn.mobiledaily.domain.mobile.ExhibitionContent

class EventSchedule extends Domain with ExhibitionContent{
    def getCollectionSuffix: String = ".event_schedule"

    @Transient
    var exhibition: Exhibition = null
    var name: String = null
    var description: String = null
    var dateFrom: Date = null
    var dateTo: Date = null
    var place: String = null

    def getExhibition = exhibition
    def getName = name
    def getDescription = description
    def getDateFrom = dateFrom
    def getDateTo = dateTo
    def getPlace = place

    def setExhibition(v: Exhibition) {exhibition = v}
    def setName(v: String) {name = v}
    def setDescription(v: String) {description = v}
    def setDateFrom(v: Date) {dateFrom = v}
    def setDateTo(v: Date) {dateTo = v}
    def setPlace(v: String) {place = v}
}
