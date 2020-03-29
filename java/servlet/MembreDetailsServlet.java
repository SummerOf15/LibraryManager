package servlet;

import model.Abonnement;
import model.Emprunt;
import model.Livre;
import model.Membre;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MembreDetailsServlet extends HttpServlet {
    private LivreService livreService= LivreServiceImpl.getInstance();
    private EmpruntService empruntService= EmpruntServiceImpl.getInstance();
    private MembreService membreService= MembreServiceImpl.getInstance();
    private int idMembre;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(req.getParameter("id")!=null){
                idMembre=Integer.parseInt(req.getParameter("id"));
                Membre membre=membreService.getById(idMembre);
                req.setAttribute("membre",membre);
                List<Emprunt> empruntList=empruntService.getListCurrentByMembre(idMembre);
                List<Livre> livreList=new ArrayList<>();
                for(Emprunt emprunt:empruntList){
                    livreList.add(livreService.getById(emprunt.getIdLivre()));
                }

                req.setAttribute("EmpruntLivres",livreList);
                req.setAttribute("EmpruntList",empruntList);
            }
        }
        catch (Exception e){
            System.out.println("MembreDetailsServlet doGet() error:"+e.toString());

        }
        req.getRequestDispatcher("/membre_details.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom=req.getParameter("nom");
        String prenom=req.getParameter("prenom");
        String adresse=req.getParameter("adresse");
        String email=req.getParameter("email");
        String telephone=req.getParameter("telephone");
        String abonnement=req.getParameter("abonnement");
        try{
            Membre membre=membreService.getById(idMembre);
            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setAdresse(adresse);
            membre.setEmail(email);
            membre.setTelephone(telephone);
            membre.setAbonnement(Abonnement.findByName(abonnement));
            membreService.update(membre);
        }
        catch (Exception e){

            throw new ServletException("MembreDetailsServlet doPost() error:"+e.toString());
        }
        resp.sendRedirect("membre_details?id="+idMembre);
    }
}
