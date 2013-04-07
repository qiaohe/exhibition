package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Sponsor;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "sponsor")
@ViewScoped
public class SponsorBean extends ExhibitionContentBean<Sponsor> {
    private Sort sort = new Sort("name");

    @Override
    protected Class<Sponsor> getContentType() {
        return Sponsor.class;
    }

    @Override
    protected Sort getSort() {
        return sort;
    }
}
