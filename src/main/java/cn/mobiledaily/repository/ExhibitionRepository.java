package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ExhibitionRepository {
    List<Exhibition> findAll();

    Exhibition findById(Long exhibitionId);
}
