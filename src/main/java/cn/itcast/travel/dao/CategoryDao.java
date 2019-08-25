package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryDao {

    /**
     * @Description:  查询所有
     * @Date:  2019/8/23
     * @Param: []
     * @return:  java.util.List<cn.itcast.travel.domain.Category>
     */
    public List<Category> findAll();


}
