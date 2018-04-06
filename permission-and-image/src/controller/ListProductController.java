package controller;

import com.googlecode.objectify.cmd.Query;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ListProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String strPage = req.getParameter("page");
            String strLimit = req.getParameter("limit");
            int page = Integer.parseInt(strPage);
            int limit = Integer.parseInt(strLimit);

            Query<Product> query = ofy().load().type(Product.class).filter("status", 1);
            int totalItem = query.count();
            int totalPage = (int) Math.ceil((double) totalItem / limit);
            List<Product> list = query.limit(limit).offset((page - 1) * limit).list();
            req.setAttribute("listProduct", list);
            req.setAttribute("totalPage", totalPage);
            req.setAttribute("currentPage", page);
            req.setAttribute("limit", limit);
            req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(500);
        }
    }
}
