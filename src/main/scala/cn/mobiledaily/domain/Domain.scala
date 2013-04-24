package cn.mobiledaily.domain

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.annotation.{LastModifiedDate, LastModifiedBy, CreatedDate, CreatedBy}
import java.util.Date
import cn.mobiledaily.domain.identity.User

trait Domain extends Serializable {
    var id: String = null
    @DBRef
    @CreatedBy
    var createdBy: User = null
    @CreatedDate
    var createdAt: Date = null
    @DBRef
    @LastModifiedBy
    var updatedBy: User = null
    @LastModifiedDate
    var updatedAt: Date = null

    def getId = id
    def getCreatedBy = createdBy
    def getCreatedAt = createdAt
    def getUpdatedBy = updatedBy
    def getUpdatedAt = updatedAt

    def setId(id: String) {this.id = id}
    def setCreatedBy(who: User) {createdBy = who}
    def setCreatedAt(when: Date) {createdAt = when}
    def setUpdatedBy(who: User) {updatedBy = who}
    def setUpdatedAt(when: Date) {updatedAt = when}
}
