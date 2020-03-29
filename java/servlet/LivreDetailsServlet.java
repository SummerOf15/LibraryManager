package servlet;

import model.Emprunt;
import model.Livre;
import model.Membre;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LivreDetailsServlet extends HttpServlet {
    private LivreService livreService= LivreServiceImpl.getInstance();
    private EmpruntService empruntService= EmpruntServiceImpl.getInstance();
    private MembreService membreService= MembreServiceImpl.getInstance();
    private int idLivre;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(req.getParameter("id")!=null){
                idLivre=Integer.parseInt(req.getParameter("id"));
                Livre livre=livreService.getById(idLivre);
                req.setAttribute("livre",livre);
                List<Emprunt> empruntList=empruntService.getListCurrentByLivre(idLivre);
                if(empruntList.size()>0){
                    Emprunt emprunt=empruntList.get(empruntList.size()-1);
                    Membre membre=membreService.getById(emprunt.getIdMembre());
                    req.setAttribute("CurrentEmprunt",emprunt);
                    req.setAttribute("Emprunteur",membre);
                }
            }
        }
        catch (Exception e){
            System.out.println("LivreDetailsServlet doGet() error:"+e.toString());

        }
        req.getRequestDispatcher("/livre_details.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Livre livre;
        try{
            String title=req.getParameter("titre");
            String author=req.getParameter("auteur");
            String isbn=req.getParameter("isbn");
            livre=new Livre(idLivre,title,author,isbn);
            livreService.update(livre);
        }
        catch (Exception e){
            throw new ServletException("LivreDetailsServlet doPost() error:"+e.toString());
        }
        resp.sendRedirect("livre_details?id="+idLivre);
    }
}
