package cn.mobiledaily.domain

import org.springframework.data.annotation.Transient
import cn.mobiledaily.domain.mobile.ExhibitionContent

class Exhibitor extends Domain with ExhibitionContent {
    def getCollectionSuffix: String = ".exhibitor"

    @Transient
    var exhibition: Exhibition = null
    var name: String = null
    var stand: String = null
    var company: String = null
    var email: String = null
    var address: String = null
    var website: String = null
    var category: String = null
    var location: String = null
    var phone: String = null
    var description: String = null

    def getExhibition = exhibition
    def getName = name
    def getStand = stand
    def getCompany = company
    def getEmail = email
    def getAddress = address
    def getWebsite = website
    def getCategory = category
    def getLocation = location
    def getPhone = phone
    def getDescription = description

    def setExhibition(v: Exhibition) {exhibition = v}
    def setName(v: String) {name = v}
    def setStand(v: String) {stand = v}
    def setCompany(v: String) {company = v}
    def setEmail(v: String) {email = v}
    def setAddress(v: String) {address = v}
    def setWebsite(v: String) {website = v}
    def setCategory(v: String) {category = v}
    def setLocation(v: String) {location = v}
    def setPhone(v: String) {phone = v}
    def setDescription(v: String) {description = v}
}
