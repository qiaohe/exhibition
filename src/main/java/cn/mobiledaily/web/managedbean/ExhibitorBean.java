package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibitor;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "exhibitor")
@ViewScoped
public class ExhibitorBean extends ExhibitionContentBean<Exhibitor> {
    private Sort sort = new Sort("location");

    @Override
    protected Class<Exhibitor> getContentType() {
        return Exhibitor.class;
    }

    @Override
    protected Sort getSort() {
        return sort;
    }
}
