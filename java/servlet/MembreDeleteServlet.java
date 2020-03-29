package servlet;

import model.Livre;
import model.Membre;
import service.MembreService;
import service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MembreDeleteServlet extends HttpServlet {
    private MembreService membreService= MembreServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("id")!=null){
                int membreId=Integer.parseInt(req.getParameter("id"));
                Membre membre=membreService.getById(membreId);
                req.setAttribute("DeleteMembre",membre);
            }
        }
        catch (Exception e){
            System.out.println("MembreDeleteServlet doGet() error"+e.toString());
            throw new ServletException();
        }
        req.getRequestDispatcher("/membre_delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("id")!=null){
                int idMembre=Integer.parseInt(req.getParameter("id"));
                membreService.delete(idMembre);
            }
        }
        catch (Exception e){
            System.out.println("MembreDeleteServlet doPost() error"+e.toString());
            throw new ServletException();
        }
        resp.sendRedirect("membre_list");
    }
}
