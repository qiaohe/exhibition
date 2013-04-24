package cn.mobiledaily.web.mbean

import javax.faces.bean.{ViewScoped, ManagedBean}
import cn.mobiledaily.web.common.SpringContext
import cn.mobiledaily.domain.Exhibition
import javax.faces.event.ActionEvent
import javax.faces.context.FacesContext
import javax.faces.application.FacesMessage

@ManagedBean(name = "exhibition")
@ViewScoped
class ExhibitionBean extends Serializable {
    val exhibitionService = SpringContext.exhibitionService
    var item: Exhibition = null
    private var isEdit: Boolean = false

    def getItems = exhibitionService.findAll

    def getItem = item

    def save(event: ActionEvent) {
        try {
            exhibitionService.save(item)
            item = null
        } catch {
            case e: Exception => FacesContext.getCurrentInstance.addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage)
            )
        }
    }

    def edit(id: String) {
        item = exhibitionService.findById(id)
        isEdit = true
    }

    def create() {
        if (isEdit || item == null) {
            item = createItem
        }
    }

    def remove(event: ActionEvent) {
        exhibitionService.delete(item)
    }

    private def createItem = {
        isEdit = false
        new Exhibition()
    }
}
