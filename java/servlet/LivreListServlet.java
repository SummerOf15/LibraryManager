package servlet;

import model.Livre;
import service.LivreService;
import service.LivreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LivreListServlet extends HttpServlet {
    private LivreService livreService= LivreServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Livre> livreList=livreService.getList();
            req.setAttribute("LivreList",livreList);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println("LivreListServlet doGet() error");
            throw new ServletException();
        }
        req.getRequestDispatcher("/livre_list.jsp").forward(req,resp);
    }
}
