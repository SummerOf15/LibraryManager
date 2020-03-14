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
import java.util.ArrayList;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpruntService empruntService= EmpruntServiceImpl.getInstance();
        LivreService livreService= LivreServiceImpl.getInstance();
        MembreService membreService= MembreServiceImpl.getInstance();
        try{
            List<Emprunt> empruntList= empruntService.getListCurrent();
            List<Livre> livreList=new ArrayList<>();
            List<Membre> membreList=new ArrayList<>();
            for(Emprunt emprunt:empruntList){
                livreList.add(livreService.getById(emprunt.getIdLivre()));
                membreList.add(membreService.getById(emprunt.getIdMembre()));
                System.out.println(emprunt.toString());
            }
            req.setAttribute("MembresNumber",membreService.count());
            req.setAttribute("LivresNumber",livreService.count());
            req.setAttribute("EmpruntsNumber",empruntService.count());
            req.setAttribute("CurrentEmprunt",empruntList);
            req.setAttribute("LivreList",livreList);
            req.setAttribute("MembreList",membreList);
        }
        catch (Exception e){
            System.out.println("get error,"+e.toString());
            e.printStackTrace();
        }
        req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
    }
}
