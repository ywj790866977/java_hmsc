package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();
    /**
     * @Description:  查询所有
     * @Date:  2019/8/23
     * @Param: [req, resp]
     * @return:  void
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取
        List<Category> list = service.findAll();
        //相应
        writeValue(list,resp);


    }

}
