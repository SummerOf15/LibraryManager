package service;

import dao.EmpruntDao;
import dao.EmpruntDaoImpl;
import dao.LivreDao;
import dao.LivreDaoImpl;
import model.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService {
    private LivreDao livreDao= LivreDaoImpl.getInstance();

    private LivreServiceImpl(){}
    private static class LivreServiceImplInstance{
        private static final LivreServiceImpl INSTANCE=new LivreServiceImpl();
    }
    public static LivreServiceImpl getInstance(){
        return LivreServiceImpl.LivreServiceImplInstance.INSTANCE;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        try {
            return livreDao.getList();
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        try{
            List<Livre> livreList=new ArrayList<>();
            EmpruntService empruntService=EmpruntServiceImpl.getInstance();
            for(Livre livre:getList()){
                if(empruntService.isLivreDispo(livre.getId())){
                    livreList.add(livre);
                }
            }
            return livreList;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        try{
            return livreDao.getById(id);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        try{
            if(titre==null)
                throw new ServiceException();
            return livreDao.create(titre,auteur,isbn);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        try {
            if(livre.getTitre()==null){
                throw new ServiceException();
            }
            livreDao.update(livre);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            livreDao.delete(id);
        }
        catch (Exception e)
        {
            throw new ServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        try{
            return livreDao.count();
        }
        catch (Exception e)
        {
            throw new ServiceException();
        }
    }
}
