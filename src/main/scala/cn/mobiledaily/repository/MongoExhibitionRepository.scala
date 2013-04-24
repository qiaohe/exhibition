package cn.mobiledaily.repository

import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import cn.mobiledaily.domain.{Speaker, Exhibition}
import java.util
import cn.mobiledaily.domain.mobile.{ExhibitionContent, Attendee}
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MongoExhibitionRepository extends ExhibitionRepository{
    @Autowired
    var mongoOperations: MongoOperations = null

    @Transactional
    def save(exhibition: Exhibition) {mongoOperations.save(exhibition)}

    @Transactional
    def save(content: ExhibitionContent) {mongoOperations.save(content)}

    def findByCode(code: String): Exhibition = mongoOperations.findOne(Query.query(Criteria.where("code").is(code)), classOf[Exhibition])

    def findAll(): util.List[Exhibition] = mongoOperations.find(new Query().`with`(new Sort(Sort.Direction.DESC, "createdAt")), classOf[Exhibition])

    def findById(id: String): Exhibition = mongoOperations.findById(id, classOf[Exhibition])

    @Transactional
    def delete(exhibition: Exhibition) {mongoOperations.remove(exhibition)}

    @Transactional
    def delete(content: ExhibitionContent) {mongoOperations.remove(content)}

    def findContents[T <: ExhibitionContent](exhibition: Exhibition, `type`: Class[T]): util.List[T] = findContents(exhibition, `type`, null)

    def findContents[T <: ExhibitionContent](exhibition: Exhibition, `type`: Class[T], sort: Sort): util.List[T] = {
        var list: util.List[T] = null
        if (sort != null) {
            list = mongoOperations.find(new Query().`with`(sort), `type`, exhibition.code + `type`.newInstance().getCollectionSuffix)
        } else {
            list = mongoOperations.findAll(`type`, exhibition.code + `type`.newInstance().getCollectionSuffix)
        }
        for (i <- 0 until list.size) {
            list.get(i).setExhibition(exhibition)
        }
        list
    }

    def findContentById[T <: ExhibitionContent](exhibition: Exhibition, id: String, `type`: Class[T]): T = {
        val t = mongoOperations.findById(id, `type`, exhibition.code + `type`.newInstance().getCollectionSuffix)
        t.setExhibition(exhibition)
        t
    }

    def findAttendeesByServiceToken(exhibition: Exhibition, token: String): util.List[Attendee] = {
        val list = mongoOperations.find(Query.query(Criteria.where("serviceToken").is(token)), classOf[Attendee], exhibition.code + new Attendee().getCollectionSuffix)
        for (i <- 0 until list.size) {
            list.get(i).setExhibition(exhibition)
        }
        list
    }
}
