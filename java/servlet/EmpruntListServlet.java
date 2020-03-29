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

public class EmpruntListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpruntService empruntService= EmpruntServiceImpl.getInstance();
        LivreService livreService= LivreServiceImpl.getInstance();
        MembreService membreService= MembreServiceImpl.getInstance();
        try{
            List<Emprunt> empruntList= empruntService.getListCurrent();
            if(req.getParameter("show")!=null){  // avoid comparing these two string directly, otherwise it will case un exception
                if(req.getParameter("show").equals("all"))
                    empruntList= empruntService.getList();
            }
            List<Livre> livreList=new ArrayList<>();
            List<Membre> membreList=new ArrayList<>();
            for(Emprunt emprunt:empruntList){
                livreList.add(livreService.getById(emprunt.getIdLivre()));
                membreList.add(membreService.getById(emprunt.getIdMembre()));
            }
            req.setAttribute("CurrentEmprunt",empruntList);
            req.setAttribute("LivreList",livreList);
            req.setAttribute("MembreList",membreList);
        }
        catch (Exception e){
            System.out.println("EmpruntListServlet doget() error,"+e.toString());
            e.printStackTrace();
        }
        req.getRequestDispatcher("/emprunt_list.jsp").forward(req,resp);
    }
}
