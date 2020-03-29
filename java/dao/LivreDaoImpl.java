package dao;

import model.Abonnement;
import model.Livre;
import model.Membre;
import persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements LivreDao {

    private LivreDaoImpl(){}
    private static class LivreDaoImplInstance{
        private static final LivreDaoImpl INSTANCE=new LivreDaoImpl();
    }
    public static LivreDaoImpl getInstance(){
        return LivreDaoImpl.LivreDaoImplInstance.INSTANCE;
    }

    @Override
    public List<Livre> getList() throws DaoException {
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        List<Livre> livreList=new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Livre";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Livre livre=new Livre();
                livre.setId(resultSet.getInt(1));
                livre.setTitre(resultSet.getString(2));
                livre.setAuteur(resultSet.getString(3));
                livre.setIsbn(resultSet.getString(4));

                livreList.add(livre);
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
        return livreList;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Livre livre=new Livre();
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet;
        Connection connection= null;
        try{
            connection= ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Livre WHERE id=?";
            connection.setAutoCommit(false);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1,id);
            resultSet=selectPreparedStatement.executeQuery();

            if(resultSet.next()){
                livre.setId(resultSet.getInt(1));
                livre.setTitre(resultSet.getString(2));
                livre.setAuteur(resultSet.getString(3));
                livre.setIsbn(resultSet.getString(4));
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
            System.out.println(e+", fail in finding the book ");
        }
        return livre;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        Connection connection=null;
        int rKey=-1;
        try{
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO Livre(titre, auteur, isbn) VALUES(?, ?, ?)";
            PreparedStatement insertPreparedStatement=connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);// insert data

            insertPreparedStatement.setString(1,titre);
            insertPreparedStatement.setString(2,auteur);
            insertPreparedStatement.setString(3,isbn);

            int num = insertPreparedStatement.executeUpdate();// insert data
            if(num>0)
            {
                System.out.println("insert successfully");
            }
            ResultSet rs=insertPreparedStatement.getGeneratedKeys();
            if(rs.next())
                rKey=rs.getInt(1);

            connection.commit();
            rs.close();
            insertPreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return rKey;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        PreparedStatement updatePreparedStatement = null;
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String UpdateQuery = "UPDATE Livre SET titre=?, auteur=?, isbn=? WHERE id=?";
            updatePreparedStatement=connection.prepareStatement(UpdateQuery);

            updatePreparedStatement.setString(1,livre.getTitre());
            updatePreparedStatement.setString(2,livre.getAuteur());
            updatePreparedStatement.setString(3, livre.getIsbn());
            updatePreparedStatement.setInt(4, livre.getId());

            int num = updatePreparedStatement.executeUpdate();// insert data
            if(num>0)
            {
                System.out.println("update successfully");
            }

            updatePreparedStatement.close();
            connection.close();
        }
        catch (Exception e){
            throw new DaoException(e+", update failed."+livre.toString());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        PreparedStatement deletePreparedStatement = null;
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "DELETE FROM Livre WHERE id=?";
            connection.setAutoCommit(false);
            deletePreparedStatement = connection.prepareStatement(SelectQuery);
            deletePreparedStatement.setInt(1,id);
            int num=deletePreparedStatement.executeUpdate();
            if(num>0)
            {
                System.out.println("delete successfully");
            }
            connection.commit();
            deletePreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            System.out.println(e+", failed in deleting ");
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
            String SelectQuery = "SELECT COUNT(id) AS count FROM Livre";
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
