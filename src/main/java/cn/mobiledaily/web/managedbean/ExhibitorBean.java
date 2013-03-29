package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibitor;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "exhibitor")
@ViewScoped
public class ExhibitorBean extends ExhibitionContentBean<Exhibitor> {
    public ExhibitorBean() {
        super(Exhibitor.class);
        setSort(new Sort("location"));
    }
}
