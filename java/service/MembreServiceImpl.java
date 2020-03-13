package service;


import dao.MembreDao;
import dao.MembreDaoImpl;

import model.Membre;

import java.util.ArrayList;
import java.util.List;

public class MembreServiceImpl implements MembreService {
    private MembreDao membreDao= MembreDaoImpl.getInstance();

    private MembreServiceImpl(){}
    private static class MembreServiceImplInstance{
        private static final MembreServiceImpl INSTANCE=new MembreServiceImpl();
    }
    public static MembreServiceImpl getInstance(){
        return MembreServiceImpl.MembreServiceImplInstance.INSTANCE;
    }

    @Override
    public List<Membre> getList() throws ServiceException {
        try{
            return membreDao.getList();
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        try{
            List<Membre> membreList=new ArrayList<>();
            EmpruntService empruntService=EmpruntServiceImpl.getInstance();
            for(Membre membre:getList()){
                if(empruntService.isEmpruntPossible(membre)){
                    membreList.add(membre);
                }
            }
            return membreList;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        try{
            return membreDao.getById(id);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
        try{
            if(nom==null || prenom==null)
                throw new ServiceException();
            return membreDao.create(nom.toUpperCase(),prenom,adresse,email,telephone);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        try {
            if(membre.getPrenom()==null || membre.getNom()==null){
                throw new ServiceException();
            }
            membreDao.update(membre);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            membreDao.delete(id);
        }
        catch (Exception e)
        {
            throw new ServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        try{
            return membreDao.count();
        }
        catch (Exception e)
        {
            throw new ServiceException();
        }
    }
}
