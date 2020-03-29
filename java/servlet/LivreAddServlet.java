package servlet;

import service.LivreService;
import service.LivreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LivreAddServlet extends HttpServlet {
    private LivreService livreService= LivreServiceImpl.getInstance();
    private int idLivre;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/livre_add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("titre");
        String author=req.getParameter("auteur");
        String isbn=req.getParameter("isbn");
        try {
            idLivre=livreService.create(title,author,isbn);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ServletException("LivreAddServlet doPost() error"+e.toString());
        }
        resp.sendRedirect("livre_details?id="+idLivre);
    }
}
