package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.News;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "news")
@ViewScoped
public class NewsBean extends ExhibitionContentBean<News> {
    private Sort sort = new Sort(Sort.Direction.DESC, "createdAt");

    @Override
    protected Class<News> getContentType() {
        return News.class;
    }

    @Override
    protected Sort getSort() {
        return sort;
    }
}
