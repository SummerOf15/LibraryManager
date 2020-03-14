package dao;

import model.Emprunt;
import persistence.ConnectionManager;
import service.EmpruntServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class EmpruntDaoImpl implements EmpruntDao{
    private EmpruntDaoImpl(){}
    private static class EmpruntDaoImplInstance{
        private static final EmpruntDaoImpl INSTANCE=new EmpruntDaoImpl();
    }
    public static EmpruntDaoImpl getInstance(){
        return EmpruntDaoImpl.EmpruntDaoImplInstance.INSTANCE;
    }

    @Override
    public List<Emprunt> getList() throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        List<Emprunt> empruntList=new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Emprunt";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Emprunt emprunt=new Emprunt();
                emprunt.setId(resultSet.getInt(1));
                emprunt.setIdMembre(resultSet.getInt(2));
                emprunt.setIdLivre(resultSet.getInt(3));
                emprunt.setDateEmprunt(resultSet.getDate(4).toLocalDate());
                if(resultSet.getDate(5)==null)
                    emprunt.setDateRetour(null);
                else
                    emprunt.setDateRetour(resultSet.getDate(5).toLocalDate());
                empruntList.add(emprunt);
            }
            connection.commit();
            resultSet.close();

            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e+", fail in getting film list");
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        List<Emprunt> empruntList=new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            System.out.println("connection succeed");
            String SelectQuery = "SELECT * FROM Emprunt WHERE dateRetour IS NULL ";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Emprunt emprunt=new Emprunt();
                emprunt.setId(resultSet.getInt(1));
                emprunt.setIdMembre(resultSet.getInt(2));
                emprunt.setIdLivre(resultSet.getInt(3));
                emprunt.setDateEmprunt(resultSet.getDate(4).toLocalDate());
                emprunt.setDateRetour(null);
                empruntList.add(emprunt);
            }
            connection.commit();
            resultSet.close();

            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            System.out.println("connection failed");
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e+", fail in getting current list");
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        List<Emprunt> empruntList=new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Emprunt WHERE idMembre =? ";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1,idMembre);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Emprunt emprunt=new Emprunt();
                emprunt.setId(resultSet.getInt(1));
                emprunt.setIdMembre(resultSet.getInt(2));
                emprunt.setIdLivre(resultSet.getInt(3));
                emprunt.setDateEmprunt(resultSet.getDate(4).toLocalDate());
                if(resultSet.getDate(5)==null)
                    emprunt.setDateRetour(null);
                else
                    emprunt.setDateRetour(resultSet.getDate(5).toLocalDate());
                empruntList.add(emprunt);
            }
            connection.commit();
            resultSet.close();

            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e+", fail in getting current list by member ID");
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        List<Emprunt> empruntList=new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Emprunt WHERE idLivre =? ";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1,idLivre);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Emprunt emprunt=new Emprunt();
                emprunt.setId(resultSet.getInt(1));
                emprunt.setIdMembre(resultSet.getInt(2));
                emprunt.setIdLivre(resultSet.getInt(3));
                emprunt.setDateEmprunt(resultSet.getDate(4).toLocalDate());
                if(resultSet.getDate(5)==null)
                    emprunt.setDateRetour(null);
                else
                    emprunt.setDateRetour(resultSet.getDate(5).toLocalDate());
                empruntList.add(emprunt);
            }
            connection.commit();
            resultSet.close();

            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e+", fail in getting current list by book ID");
        }
        return empruntList;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        Emprunt emprunt=new Emprunt();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Emprunt WHERE idLivre =? ";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1,id);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                emprunt.setId(resultSet.getInt(1));
                emprunt.setIdMembre(resultSet.getInt(2));
                emprunt.setIdLivre(resultSet.getInt(3));
                emprunt.setDateEmprunt(resultSet.getDate(4).toLocalDate());
                if(resultSet.getDate(5)==null)
                    emprunt.setDateRetour(null);
                else
                    emprunt.setDateRetour(resultSet.getDate(5).toLocalDate());
            }
            connection.commit();
            resultSet.close();

            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e+", fail in getting current list by book ID");
        }
        return emprunt;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        Connection connection=null;
        int rKey=-1;
        try{
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO Emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES(?, ?, ?,null)";
            PreparedStatement insertPreparedStatement=connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);// insert data

            insertPreparedStatement.setInt(1,idMembre);
            insertPreparedStatement.setInt(2,idLivre);
            insertPreparedStatement.setObject(3,dateEmprunt);

            int num = insertPreparedStatement.executeUpdate();// insert data
            if(num>0)
            {
                System.out.println("insert successfully");
            }
            connection.commit();
            insertPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        Connection connection=null;
        try{
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            String updateQuery = "UPDATE Emprunt SET idMembre=?, idLivre=?, dateEmprunt=?, dateRetour=? WHERE Id=?";
            PreparedStatement updatePreparedStatement=connection.prepareStatement(updateQuery);// insert data

            updatePreparedStatement.setInt(1,emprunt.getIdMembre());
            updatePreparedStatement.setInt(2,emprunt.getIdLivre());
            updatePreparedStatement.setObject(3,emprunt.getDateEmprunt());
            updatePreparedStatement.setObject(4,emprunt.getDateRetour());
            updatePreparedStatement.setInt(5,emprunt.getId());

            int num = updatePreparedStatement.executeUpdate();// insert data
            if(num>0)
            {
                System.out.println("update successfully");
            }
            connection.commit();
            updatePreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int count() throws DaoException {
        int totalNum=0;
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        Connection connection= null;
        try{
            connection= ConnectionManager.getConnection();
            String SelectQuery = "SELECT COUNT(id) AS count FROM Emprunt";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            resultSet=selectPreparedStatement.executeQuery();
            if(resultSet.next())
                totalNum=resultSet.getInt(1);

            connection.commit();
            resultSet.close();
            selectPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            System.out.println(e+", fail in counting ");
        }
        return totalNum;
    }
}
