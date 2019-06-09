package Model;

import java.sql.*;
import java.util.ArrayList;

public class UsersDatabase extends Database {

    public UsersDatabase(){
        super();
        this.tableName = "users_table";
        String sql = "CREATE TABLE IF NOT EXISTS users_table (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "	rank string NOT NULL,\n"
                + "	status text NOT NULL,\n"
                + "	type text NOT NULL\n"
                + ");";
        try{
            // create a new table
            Statement stmt = currentConnection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    //tuple structure as array:
    //0-username
    //1-password
    //2-name
    //3-rank
    //4-status
    //5-type
    public void createTuple(String[] tuple){
        if(tuple.length!=6)
            throw new RuntimeException("Incorrect tuple size, cannot index");
        String sql = "INSERT INTO users_table (username,password,name,rank,status,type) VALUES(?,?,?,?,?,?)";
        this.executeUpdateStatement(sql,tuple);
    }
    public void editTuple(String field, String newValue, String username){
        String sql = "UPDATE users_table SET "+field+" = ? "
                + "WHERE username = ?";
        String[] args = {newValue,username};
        this.executeUpdateStatement(sql,args);
    }

    public void deleteTuple(String username){
        String sql = "DELETE FROM users_table WHERE username = ?";
        String[] args = {username};
        this.executeUpdateStatement(sql,args);
    }

    public User getByUsername(String username){
        String sql = "SELECT * " + "FROM users_table WHERE username = ?";
        String[] args = {username};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }

    public ArrayList<Policeman> getAllPolicemen(){
        String sql = "SELECT * " + "FROM users_table WHERE type = ?";
        String[] args = {"Policeman"};
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<Policeman> res = new ArrayList<Policeman>();
        try{
            while (rs.next()) {
                res.add(new Policeman(rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("rank"),
                        rs.getString("status"), rs.getString("type")));


            }
        }
        catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    public ArrayList<Fireman> getAllFiremen(){
        String sql = "SELECT * " + "FROM users_table WHERE type = ?";
        String[] args = {"Fireman"};
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<Fireman> res = new ArrayList<Fireman>();
        try{
            while (rs.next()) {
                res.add(new Fireman(rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("rank"),
                        rs.getString("status"), rs.getString("type")));


            }
        }
        catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    public ArrayList<Dispatcher> getAllDispatchers(){
        String sql = "SELECT * " + "FROM users_table WHERE type = ?";
        String[] args = {"Dispatcher"};
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<Dispatcher> res = new ArrayList<Dispatcher>();
        try{
            while (rs.next()) {
                res.add(new Dispatcher(rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("rank"),
                        rs.getString("status"), rs.getString("type")));


            }
        }
        catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public ArrayList<EmergencyMedicalTechnician> getAllEmergencyMedicalTechnicians(){
        String sql = "SELECT * " + "FROM users_table WHERE type = ?";
        String[] args = {"EmergencyMedicalTechnician"};
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<EmergencyMedicalTechnician> res = new ArrayList<EmergencyMedicalTechnician>();
        try{
            while (rs.next()) {
                res.add(new EmergencyMedicalTechnician(rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("rank"),
                        rs.getString("status"), rs.getString("type")));


            }
        }
        catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    private User parseResultSet(ResultSet rs){
        boolean exists;
        try {
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(!exists)
            return null;
        try {
            String[] user = {rs.getString("username"), rs.getString("password"),
                    rs.getString("name"), rs.getString("rank"),
                    rs.getString("status"), rs.getString("type")};
            if(user[5].equals("Policeman"))
            {
                return new Policeman(user[0],user[1],user[2], user[3],user[4],user[5]);
            }
            if(user[5].equals("Fireman"))
            {
                return new Fireman(user[0],user[1],user[2], user[3],user[4],user[5]);
            }
            if(user[5].equals("Dispatcher"))
            {
                return new Dispatcher(user[0],user[1],user[2], user[3],user[4],user[5]);
            }
            if(user[5].equals("EmergencyMedicalTechnician"))
            {
                return new EmergencyMedicalTechnician(user[0],user[1],user[2], user[3],user[4],user[5]);
            }

        }
        catch (SQLException e){
            System.out.println("Information retrieval error.");
        }
        System.out.println("no users from type !");
        return null;
    }

}