package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取uri
        String uri = req.getRequestURI();
        System.out.println(uri);
        //获取uri中方法名
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println(methodName);

        //反射调用方法
        try {
            Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
