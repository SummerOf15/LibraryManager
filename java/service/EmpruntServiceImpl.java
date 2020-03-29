package service;

import dao.EmpruntDao;
import dao.EmpruntDaoImpl;
import model.Emprunt;
import model.Membre;

import java.time.LocalDate;

import java.util.List;

public class EmpruntServiceImpl implements EmpruntService{
    private EmpruntDao empruntDao=EmpruntDaoImpl.getInstance();
    private MembreService membreService=MembreServiceImpl.getInstance();

    private EmpruntServiceImpl(){}
    private static class EmpruntServiceImplInstance{
        private static final EmpruntServiceImpl INSTANCE=new EmpruntServiceImpl();
    }
    public static EmpruntServiceImpl getInstance(){
        return EmpruntServiceImplInstance.INSTANCE;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException {
        try{
            return empruntDao.getList();
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        try{
            return empruntDao.getListCurrent();
        }
        catch (Exception e){
            System.out.println("getListCurrent() error");
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        try{
            return empruntDao.getListCurrentByMembre(idMembre);
        }
        catch (Exception e){
            System.out.println("getListCurrentByMembre() error");
            throw new ServiceException();
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        try{
            return empruntDao.getListCurrentByLivre(idLivre);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        try{
            return empruntDao.getById(id);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        try{
            Membre membre=membreService.getById(idMembre);
            if(this.isEmpruntPossible(membre))
                empruntDao.create(idMembre,idLivre,dateEmprunt);
            else
                throw new ServiceException("Achieve the maximum number of borrowed books");
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void returnBook(int id) throws ServiceException {
        try{
            List<Emprunt> empruntList=empruntDao.getListCurrentByLivre(id);
            empruntList.get(empruntList.size()-1).setDateRetour(LocalDate.now());
            empruntDao.update(empruntList.get(empruntList.size()-1));
        }
        catch (Exception e){
            throw new ServiceException();
        }

    }

    @Override
    public int count() throws ServiceException {
        try{
            return empruntDao.count();
        }
        catch (Exception e){
            throw new ServiceException();
        }

    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        try{
            List<Emprunt> empruntList=empruntDao.getListCurrentByLivre(idLivre);
            if(empruntList.size()>0 && empruntList.get(empruntList.size()-1).getDateRetour() != null){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw new ServiceException();
        }
        return false;
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        try{
            List<Emprunt> empruntList=empruntDao.getListCurrentByMembre(membre.getId());
            if(empruntList.size()>=membre.getAbonnement().getValue()){
                return false;
            }
            else
                return true;
        }
        catch (Exception e){
            throw new ServiceException("isEmpruntPossible error when check Membre:"+membre.toString());
        }
    }
}
