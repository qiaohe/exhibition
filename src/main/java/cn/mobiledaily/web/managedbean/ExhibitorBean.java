package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibitor;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "exhibitor")
@ViewScoped
public class ExhibitorBean extends ExhibitionContentBean<Exhibitor> {
    @PostConstruct
    private void init() {
        setContentType(Exhibitor.class);
        setSort(new Sort("location"));
    }
}
