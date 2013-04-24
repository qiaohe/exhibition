package cn.mobiledaily.repository

import cn.mobiledaily.service.ExhibitionService
import cn.mobiledaily.domain.mobile.ExhibitionContent
import org.springframework.data.domain.Sort
import java.util
import cn.mobiledaily.domain.Exhibition
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import java.util.Date

@Service("exhibitionService")
@Transactional(readOnly = true)
class ExhibitionServiceImpl extends ExhibitionService{
    @Autowired
    var exhibitionRepository: ExhibitionRepository = null

    def save(exhibition: Exhibition) {exhibitionRepository.save(exhibition)}

    def save(content: ExhibitionContent) {
        val exhibition = content.getExhibition
        exhibition.updatedAt = new Date()
        exhibitionRepository.save(content)
        exhibitionRepository.save(exhibition)
    }

    def findAll(): util.List[Exhibition] = exhibitionRepository.findAll()

    def findByCode(code: String): Exhibition = exhibitionRepository.findByCode(code)

    def findById(id: String): Exhibition = exhibitionRepository.findById(id)

    def delete(exhibition: Exhibition) {exhibitionRepository.delete(exhibition)}

    def findContents[T <: ExhibitionContent](exhibitionCode: String, `type`: Class[T]): util.List[T] = findContents(exhibitionCode, `type`, null)

    def findContents[T <: ExhibitionContent](exhibitionCode: String, `type`: Class[T], sort: Sort): util.List[T] = {
        val exhibition = findByCode(exhibitionCode)
        exhibitionRepository.findContents(exhibition, `type`, sort)
    }

    def findContentById[T <: ExhibitionContent](exhibitionCode: String, id: String, `type`: Class[T]): T = {
        try {
            val exhibition = findByCode(exhibitionCode)
            exhibitionRepository.findContentById(exhibition, id, `type`)
        } catch {
            case e: Exception => throw new RuntimeException(e)
        }
    }

    def delete[T <: ExhibitionContent](content: ExhibitionContent) {exhibitionRepository.delete(content)}
}
