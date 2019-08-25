package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @ClassName: RouteServiceImpl
 * @Description:
 * @Author: niunan
 * @Create: 2019/8/23 15:23
 **/
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();


    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pagebean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置页大小
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置数据
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);
        //设置总页数
        int totalPage = totalCount % pageSize  == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }


    @Override
    public Route findOne(String rid) {
        //查询route信息
        Route route = routeDao.findOne(Integer.parseInt(rid));

        //根据route id 查询图片信息;
        List<RouteImg> imgs = routeImgDao.findByRid(Integer.parseInt(rid));
        route.setRouteImgList(imgs);

        //根据route的sid查询上家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        // 查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
