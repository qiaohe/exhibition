package cn.mobiledaily.domain.mobile;

import cn.mobiledaily.domain.Exhibition;

public interface ExhibitionContent {
    String getCollectionSuffix();
    Exhibition getExhibition();
    void setExhibition(Exhibition exhibition);
}
