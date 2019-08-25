package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service  = new UserServiceImpl();
    /**
     * @Description:  注册功能
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //校验验证码
        System.out.println("执行注册方法");
        //获取用户验证码
        String check = req.getParameter("check");
        //获取servlet验证码
        HttpSession session = req.getSession();
//        System.out.println("regist:"+session.getId());
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //返回数据
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        //1.获取参数
        Map<String, String[]> map = req.getParameterMap();
        //2.封装参数
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.完成注册
//        UserService service  = new UserServiceImpl();
        boolean flag = service.regist(user);

        //4.相应结果
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        //序列化为json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
        String json = writeValueAsString(info);

        //返回数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    /**
     * @Description:  登录功能
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //校验验证码
        System.out.println("执行login方法");
        String check = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //返回数据
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        //1.获取参数
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2 查询数据
//        UserService service = new UserServiceImpl();
        User u =  service.login(user);

        //3 判断
        ResultInfo info = new ResultInfo();
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户或密码错误");
        }

        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("用户没有激活, 请激活之后再登录");
        }

        if(u != null && "Y".equals(u.getStatus())){
            session.setAttribute("user",u);
            info.setFlag(true);
        }

        //相应数据
//        ObjectMapper mapper = new ObjectMapper();

        //设置响应消息类型
//        resp.setContentType("application/json;charset=utf-8");
        //使用mapper的方法直接响应消息
//        mapper.writeValue(resp.getOutputStream(),info);
        writeValue(info,resp);
    }

    /**
     * @Description:  查询1个用户
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
//        ObjectMapper mapper = new ObjectMapper();
//        resp.setContentType("application/json:charset=utf-8");
//        mapper.writeValue(resp.getOutputStream(),user);
        writeValue(user,resp);
    }

    /**
     * @Description:  退出
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("执行exit方法");
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }

    /**
     * @Description:  激活功能
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取激活码
        String code = req.getParameter("code");
        if(code != null){
            //查询激活码是否存在
//            UserService service = new UserServiceImpl();
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
}
