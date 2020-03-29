package servlet;

import model.Livre;
import service.EmpruntService;
import service.EmpruntServiceImpl;
import service.LivreService;
import service.LivreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LivreDeleteServlet extends HttpServlet {
    private LivreService livreService= LivreServiceImpl.getInstance();
    private EmpruntService empruntService= EmpruntServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("id")!=null){
                int livreId=Integer.parseInt(req.getParameter("id"));
                Livre livre=livreService.getById(livreId);
                req.setAttribute("DeleteLivre",livre);
            }
        }
        catch (Exception e){
            System.out.println("LivreDeleteServlet doGet() error"+e.toString());
            throw new ServletException();
        }
        req.getRequestDispatcher("/livre_delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("id")!=null){
                int idLivre=Integer.parseInt(req.getParameter("id"));
                livreService.delete(idLivre);

            }
        }
        catch (Exception e){
            System.out.println("LivreDeleteServlet doPost() error"+e.toString());
            throw new ServletException();
        }
        resp.sendRedirect("livre_list");
    }
}
