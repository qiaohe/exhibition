package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.EventSchedule;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "eventSchedule")
@ViewScoped
public class EventScheduleBean extends ExhibitionContentBean<EventSchedule> {
    @PostConstruct
    private void init() {
        setContentType(EventSchedule.class);
        setSort(new Sort("dateFrom", "dateTo"));
    }
}
