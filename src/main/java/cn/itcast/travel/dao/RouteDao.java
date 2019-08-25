package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * @Description:  查询总记录数
     * @Date:  2019/8/23
     * @Param: [cid]
     * @return:  int
     */
    public int findTotalCount(int cid,String rname);

    /**
     * @Description:  查询记录
     * @Date:  2019/8/23
     * @Param: 
     * @return:  
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * @Description:  根据id查询
     * @Date:  2019/8/24
     * @Param: [rid]
     * @return:  cn.itcast.travel.domain.Route
     */
    public Route findOne(int rid);
}
