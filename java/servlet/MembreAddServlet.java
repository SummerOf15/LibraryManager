package servlet;

import service.MembreService;
import service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MembreAddServlet extends HttpServlet {
    private MembreService membreService= MembreServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/membre_add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom=req.getParameter("nom");
        String prenom=req.getParameter("prenom");
        String adresse=req.getParameter("adresse");
        String email=req.getParameter("email");
        String telephone=req.getParameter("telephone");
        int idMembre;
        try {
            idMembre=membreService.create(nom,prenom,adresse,email,telephone);

        }
        catch (Exception e){
            throw new ServletException("LivreAddServlet doPost() error:"+e.toString());
        }
        resp.sendRedirect("membre_details?id="+idMembre);
    }
}
