package cn.mobiledaily.domain

import cn.mobiledaily.domain.mobile.ExhibitionContent
import org.springframework.data.annotation.Transient

class Speaker extends Domain with ExhibitionContent{
    def getCollectionSuffix: String = ".speaker"

    @Transient
    var exhibition: Exhibition = null
    var name: String = null
    var profile: String = null
    var email: String = null
    var position: String = null
    var company: String = null
    var mobilePhone: String = null
    var photo: String = null

    def getExhibition = exhibition
    def getName = name
    def getProfile = profile
    def getEmail = email
    def getPosition = position
    def getCompany = company
    def getMobilePhone = mobilePhone
    def getPhoto = photo

    def setExhibition(v: Exhibition) {exhibition = v}
    def setName(v: String) {name = v}
    def setProfile(v: String) {profile = v}
    def setEmail(v: String) {email = v}
    def setPosition(v: String) {position = v}
    def setCompany(v: String) {company = v}
    def setMobilePhone(v: String) {mobilePhone = v}
    def setPhoto(v: String) {photo = v}
}
