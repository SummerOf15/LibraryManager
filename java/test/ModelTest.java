package test;

import dao.EmpruntDao;
import dao.EmpruntDaoImpl;
import dao.MembreDao;
import dao.MembreDaoImpl;
import model.Abonnement;
import model.Emprunt;
import model.Livre;
import model.Membre;

import java.util.List;
import java.time.LocalDate;
public class ModelTest {
    public static void main(String [] args){
        Emprunt emprunt=new Emprunt(5,3,3,LocalDate.of(2018,10,10),LocalDate.of(2019,1,1));
        Livre livre=new Livre();
        MembreDao membreDao = MembreDaoImpl.getInstance();
        Membre membre=new Membre(13,"wei","cc","china","lovec@ck.com","21212121", Abonnement.VIP);
        EmpruntDao empruntDao= EmpruntDaoImpl.getInstance();
        try{
            empruntDao.update(emprunt);
            Emprunt emprunt1=empruntDao.getById(5);
            System.out.println(emprunt1.toString());
//            int num=membreDao.create("z","k","ssd","h@hho.com","1122334");
//            System.out.println(membreDao.getById(14).toString());
//            membreDao.update(membre);
            System.out.println(empruntDao.count());
//            membreList=membreDao.getList();
//            System.out.println(membreList.get(1).toString());
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }

        System.out.println(emprunt.toString());
        System.out.println(livre.toString());

    }
}
