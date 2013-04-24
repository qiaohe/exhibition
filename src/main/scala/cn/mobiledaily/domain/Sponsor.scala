package cn.mobiledaily.domain

import cn.mobiledaily.domain.mobile.ExhibitionContent
import org.springframework.data.annotation.Transient

class Sponsor extends Domain with ExhibitionContent{
    def getCollectionSuffix: String = ".sponsor"

    @Transient
    var exhibition: Exhibition = null
    var name: String = null
    var bannerImage: String = null
    var website: String = null

    def getExhibition = exhibition
    def getName = name
    def getBannerImage = bannerImage
    def getWebsite = website

    def setExhibition(v: Exhibition) {exhibition = v}
    def setName(v: String) {name = v}
    def setBannerImage(v: String) {bannerImage = v}
    def setWebsite(v: String) {website = v}
}
