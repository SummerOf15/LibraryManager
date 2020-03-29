package dao;

import model.Abonnement;
import model.Membre;
import persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MembreDaoImpl implements MembreDao{

    private MembreDaoImpl(){}
    private static class MembreDaoImplInstance{
        private static final MembreDaoImpl INSTANCE=new MembreDaoImpl();
    }
    public static MembreDaoImpl getInstance(){
        return MembreDaoImpl.MembreDaoImplInstance.INSTANCE;
    }

    @Override
    public List<Membre> getList() throws DaoException {
        ResultSet resultSet;
        List<Membre> membreList=new ArrayList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Membre";
            connection.setAutoCommit(false);
            PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
            resultSet=selectPreparedStatement.executeQuery();// fetch data
            while(resultSet.next())
            {
                Membre membre=new Membre();
                membre.setId(resultSet.getInt(1));
                membre.setNom(resultSet.getString(2));
                membre.setPrenom(resultSet.getString(3));
                membre.setAdresse(resultSet.getString(4));
                membre.setEmail(resultSet.getString(5));
                membre.setTelephone(resultSet.getString(6));
                membre.setAbonnement(Abonnement.findByName(resultSet.getString(7)));

                membreList.add(membre);
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
        return membreList;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        Membre membre=new Membre();
        ResultSet resultSet;
        try{
            Connection connection= ConnectionManager.getConnection();
            String SelectQuery = "SELECT * FROM Membre WHERE id=?";
            connection.setAutoCommit(false);
            PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1,id);
            resultSet=selectPreparedStatement.executeQuery();
            if(resultSet.next()){
                membre.setId(resultSet.getInt(1));
                membre.setNom(resultSet.getString(2));
                membre.setPrenom(resultSet.getString(3));
                membre.setAdresse(resultSet.getString(4));
                membre.setEmail(resultSet.getString(5));
                membre.setTelephone(resultSet.getString(6));
                membre.setAbonnement(Abonnement.findByName(resultSet.getString(7)));
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
            System.out.println(e+", fail in finding the member ");
        }
        return membre;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        Connection connection=null;
        int rKey=-1;
        try{
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO Membre(nom, prenom, adresse, email, telephone, abonnement) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPreparedStatement=connection.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);// insert data

            insertPreparedStatement.setString(1,nom);
            insertPreparedStatement.setString(2,prenom);
            insertPreparedStatement.setString(3,adresse);
            insertPreparedStatement.setString(4,email);
            insertPreparedStatement.setString(5,telephone);
            insertPreparedStatement.setString(6,Abonnement.BASIC.getName());

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
    public void update(Membre membre) throws DaoException {
        PreparedStatement updatePreparedStatement = null;
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String UpdateQuery = "UPDATE Membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?";
            updatePreparedStatement=connection.prepareStatement(UpdateQuery);
            updatePreparedStatement.setString(1,membre.getNom());
            updatePreparedStatement.setString(2,membre.getPrenom());
            updatePreparedStatement.setString(3, membre.getAdresse());
            updatePreparedStatement.setString(4, membre.getEmail());
            updatePreparedStatement.setString(5, membre.getTelephone());
            updatePreparedStatement.setString(6, membre.getAbonnement().getName());
            updatePreparedStatement.setInt(7, membre.getId());
            int num = updatePreparedStatement.executeUpdate();// insert data
            if(num>0)
            {
                System.out.println("update successfully");
            }

            updatePreparedStatement.close();
            connection.close();
        }
        catch (SQLException se){
            throw new DaoException();
        }
        catch (Exception e){
            System.out.println(membre.toString());
            throw new DaoException(e+", update failed."+membre.toString());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        PreparedStatement deletePreparedStatement = null;
        Connection connection = null;
        try{
            connection = ConnectionManager.getConnection();
            String SelectQuery = "DELETE FROM Membre WHERE id=?";
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
            String SelectQuery = "SELECT COUNT(id) AS count FROM Membre";
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
