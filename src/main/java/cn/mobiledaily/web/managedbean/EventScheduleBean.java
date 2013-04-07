package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.EventSchedule;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "eventSchedule")
@ViewScoped
public class EventScheduleBean extends ExhibitionContentBean<EventSchedule> {
    private Sort sort = new Sort("dateFrom", "dateTo");

    @Override
    protected Class<EventSchedule> getContentType() {
        return EventSchedule.class;
    }

    @Override
    protected Sort getSort() {
        return sort;
    }
}
