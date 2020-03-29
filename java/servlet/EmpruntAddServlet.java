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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntAddServlet extends HttpServlet {
    private EmpruntService empruntService= EmpruntServiceImpl.getInstance();
    private LivreService livreService= LivreServiceImpl.getInstance();
    private MembreService membreService=MembreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Livre> livreList=new ArrayList<>();
        List<Membre> membreList=new ArrayList<>();

        try{
            for(Livre livre: livreService.getList()){
                if(empruntService.isLivreDispo(livre.getId())){
                    livreList.add(livre);
                }
            }
            for(Membre membre: membreService.getList()){
                if(empruntService.isEmpruntPossible(membre)){
                    membreList.add(membre);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ServletException("EmpruntAddServlet doGet() error"+e.toString());
        }

        req.setAttribute("AvailableLivres",livreList);
        req.setAttribute("AvailableMembres",membreList);
        req.getRequestDispatcher("/emprunt_add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLivre=Integer.parseInt(req.getParameter("idLivre"));
        int idMembre=Integer.parseInt(req.getParameter("idMembre"));
        int idEmprunt;
        try {
            empruntService.create(idMembre,idLivre, LocalDate.now());
            List<Emprunt> empruntList=empruntService.getListCurrentByLivre(idLivre);
            idEmprunt=empruntList.get(empruntList.size()-1).getId();
        }
        catch (Exception se){
            se.printStackTrace();
            System.out.println(se.toString());
            System.out.println("EmpruntAddServlet doPost() error");
            throw new ServletException();
        }
        resp.sendRedirect("emprunt_list?id="+idEmprunt);
    }
}
