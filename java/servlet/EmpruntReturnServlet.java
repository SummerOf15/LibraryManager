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

public class EmpruntReturnServlet extends HttpServlet {
    private EmpruntService empruntService= EmpruntServiceImpl.getInstance();
    private LivreService livreService= LivreServiceImpl.getInstance();
    private MembreService membreService= MembreServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Emprunt> empruntList=new ArrayList<>();
            int returnIdLivre=0;
            if(req.getParameter("id")!=null){
                returnIdLivre=Integer.parseInt(req.getParameter("id"));
                List<Emprunt> currLivreList=empruntService.getListCurrentByLivre(returnIdLivre);
                if(currLivreList.size()>0)
                    empruntList.add(currLivreList.get(currLivreList.size()-1));
            }
            else{
                empruntList=empruntService.getListCurrent();
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
            System.out.println("EmpruntReturnServlet doGet() error."+e.toString());
            throw new ServletException();
        }
        req.getRequestDispatcher("/emprunt_return.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int retureIdLivre=Integer.parseInt(req.getParameter("id"));
        try{
            empruntService.returnBook(retureIdLivre);
        }
        catch (Exception e){
            System.out.println("EmpruntReturnServlet doPost() error."+e.toString());
            throw new ServletException();
        }
        resp.sendRedirect("emprunt_list");
    }
}
