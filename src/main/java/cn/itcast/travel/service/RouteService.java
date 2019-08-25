package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    /**
     * @Description:  分页查询
     * @Date:  2019/8/24
     * @Param: [cid, currentPage, pageSize, rname]
     * @return:  cn.itcast.travel.domain.PageBean<cn.itcast.travel.domain.Route>
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * @Description:  根据id查询商品信息
     * @Date:  2019/8/24
     * @Param: [rid]
     * @return:  cn.itcast.travel.domain.Route
     */
    Route findOne(String rid);

}
