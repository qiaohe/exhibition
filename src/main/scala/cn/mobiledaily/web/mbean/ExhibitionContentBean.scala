package cn.mobiledaily.web.mbean

import cn.mobiledaily.web.common.SpringContext
import javax.faces.bean.ManagedProperty
import cn.mobiledaily.web.managedbean.UserBean
import org.springframework.data.domain.Sort
import java.util
import cn.mobiledaily.domain.mobile.ExhibitionContent
import javax.faces.context.FacesContext
import javax.faces.application.FacesMessage

trait ExhibitionContentBean[T <: ExhibitionContent] extends Serializable {
    val exhibitionService = SpringContext.exhibitionService
    @ManagedProperty("#{userBean")
    var userBean: UserBean = null
    var sort = new Sort(Sort.Direction.DESC, "createdAt")
    var item: T = _
    private val isEdit: Boolean = false

    def setUserBean(bean: UserBean) {
        userBean = bean
    }

    def getItems: util.List[T] = {
        exhibitionService.findContents(userBean.getExhibitionCode, classOf[T], sort)
    }

    def getItem = item

    def save() {
        try {
            exhibitionService.save(item)
            item = _
        } catch {
            case e: Exception => FacesContext.getCurrentInstance.addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage)
            )
        }
    }

//    def createItem: T = {
//
//    }

    def create() {
        if (isEdit || item == null.asInstanceOf[T]) {
//            item = createItem
        }
    }
}
