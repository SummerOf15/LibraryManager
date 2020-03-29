package servlet;

import model.Membre;
import service.MembreService;
import service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MembreListServlet extends HttpServlet {
    private MembreService membreService= MembreServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Membre> membreList=membreService.getList();
            req.setAttribute("MembreList",membreList);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("MembreListServlet doGet() error:"+e.toString());
            throw new ServletException();
        }
        req.getRequestDispatcher("/membre_list.jsp").forward(req,resp);

    }
}
