package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取uri
        String uri = req.getRequestURI();
        //获取uri中方法名
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        //反射调用方法
        try {
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
//            method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @Description:  json
     * @Date:  2019/8/23
     * @Param: [o]
     * @return:  void
     */
    public void writeValue(Object o,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),o);
    }

    /**
     * @Description:  json字符串
     * @Date:  2019/8/23
     * @Param:
     * @return:
     */
    public String writeValueAsString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
