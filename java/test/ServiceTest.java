package test;

import model.Abonnement;
import model.Membre;
import service.EmpruntService;
import service.EmpruntServiceImpl;
import service.MembreService;
import service.MembreServiceImpl;

import java.time.LocalDate;

public class ServiceTest {
    public static void main(String [] args){
        EmpruntService empruntService= EmpruntServiceImpl.getInstance();
        Membre membre=new Membre(20,"z","cc","paris","ee@paris.com","0011", Abonnement.VIP);
        MembreService membreService= MembreServiceImpl.getInstance();
        try {
//            membreService.create(membre.getNom(),membre.getPrenom(),membre.getAdresse(),membre.getEmail(),membre.getTelephone());
//            membreService.update(membre);
//
//            empruntService.create(20,1, LocalDate.of(2020,2,2));
//            empruntService.create(20,2,LocalDate.of(2020,2,2));
//            System.out.println(empruntService.isEmpruntPossible(membre));
//            empruntService.returnBook(1);
//            System.out.println(empruntService.isEmpruntPossible(membre));
//            System.out.println("id20:"+membreService.getById(10));
//            System.out.println("current list"+empruntService.getListCurrent().toString());
            System.out.println("livre disponible:"+empruntService.getListCurrentByLivre(10));
            System.out.println(empruntService.getListCurrent());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
