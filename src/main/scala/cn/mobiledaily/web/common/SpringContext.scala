package cn.mobiledaily.web.common

import javax.faces.context.FacesContext
import cn.mobiledaily.service.{AttendeeService, FileService, ExhibitionService}
import cn.mobiledaily.web.assembler.CheckInAssembler

object SpringContext {
    private def get[T](name: String, clazz: Class[T]) = {
        val facesContext = FacesContext.getCurrentInstance
        facesContext.getApplication.evaluateExpressionGet(facesContext, "#{" + name + "}", clazz)
    }

    def exhibitionService = get("exhibitionService", classOf[ExhibitionService])

    def fileService = get("fileService", classOf[FileService])

    def attendeeService = get("attendeeService", classOf[AttendeeService])

    def checkInAssembler = get("checkInAssembler", classOf[CheckInAssembler])
}
