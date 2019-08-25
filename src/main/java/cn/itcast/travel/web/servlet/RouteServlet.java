package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * @Description: 查询分页
     * @Date: 2019/8/24
     * @Param: [req, resp]
     * @return: void
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String currentPageStr = req.getParameter("currentPage");
        String pageSzieStr = req.getParameter("pageSzie");
        String cidStr = req.getParameter("cid");
        String rname = req.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        //转换
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSzieStr != null && pageSzieStr.length() > 0) {
            pageSize = Integer.parseInt(pageSzieStr);
        } else {
            pageSize = 5;
        }

        //3.调用方法查询
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);

        //4.json 返回
        writeValue(pb, resp);
    }

    /**
     * @Description: 商品详情
     * @Date: 2019/8/24
     * @Param: [req, resp]
     * @return: void
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String rid = req.getParameter("rid");

        //查询
        Route route = service.findOne(rid);

        //返回
        writeValue(route, resp);
    }

    /**
     * @Description: 收藏
     * @Date: 2019/8/25
     * @Param: [req, resp]
     * @return: void
     */
    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String rid = req.getParameter("rid");

        //获取当前登录用户
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //查询
        boolean flag = favoriteService.isFavorite(rid, uid);

        writeValue(flag, resp);

    }

    /**
     * @Description:  添加收藏
     * @Date:  2019/8/25
     * @Param: [req, resp]
     * @return:  void
     */
    public void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String rid = req.getParameter("rid");

        //获取当前登录用户
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            return;
        } else {
            uid = user.getUid();
        }

        //查询
        favoriteService.add(rid,uid);
    }
}