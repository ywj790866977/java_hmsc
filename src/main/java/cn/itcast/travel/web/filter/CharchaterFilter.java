package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
