package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Sponsor;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "sponsor")
@ViewScoped
public class SponsorBean extends ExhibitionContentBean<Sponsor> {
    public SponsorBean() {
        super(Sponsor.class);
        setSort(new Sort("name"));
    }
}
