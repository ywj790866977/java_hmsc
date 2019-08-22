package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取激活码
        String code = req.getParameter("code");
        if(code != null){
            //查询激活码是否存在
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            // 转发

            String msg = null;
            if(flag){
                // 激活成功
                msg = "激活成功,清<a href='login.html'>登录</a>";
            }else{
                msg = "激活失败,请联系管理员";

            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
        //查询
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
