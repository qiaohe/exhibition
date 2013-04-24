package cn.mobiledaily.domain

import java.util.Date

class Exhibition extends Domain {
    var code: String = null
    var name: String = null
    var description: String = null
    var address: String = null
    var startDate: Date = null
    var endDate: Date = null
    var organizer: String = null

    def getCode = code
    def getName = name
    def getDescription = description
    def getAddress = address
    def getStartDate = startDate
    def getEndDate = endDate
    def getOrganizer = organizer

    def setCode(v: String) {code = v}
    def setName(v: String) {name = v}
    def setDescription(v: String) {description = v}
    def setAddress(v: String) {address = v}
    def setStartDate(v: Date) {startDate = v}
    def setEndDate(v: Date) {endDate = v}
    def setOrganizer(v: String) {organizer = v}
}
