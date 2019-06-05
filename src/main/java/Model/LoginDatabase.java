package Model;

import java.sql.*;

public class LoginDatabase extends Database {

    public LoginDatabase(){
        super();
        this.tableName = "users_table";
        String sql = "CREATE TABLE IF NOT EXISTS login_table (\n"
                + "	login text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "	rank integer NOT NULL,\n"
                + "	status text NOT NULL,\n"
                + "	division text NOT NULL,\n"
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
    //0-login
    //1-password
    //2-fname
    //3-lname
    //4-city
    public void createTuple(String[] tuple){
        if(tuple.length!=7)
            throw new RuntimeException("Incorrect tuple size, cannot index");
        String sql = "INSERT INTO login_table (login,password,name,rank,status,division,type) VALUES(?,?,?,?,?,?,?)";
        this.executeUpdateStatement(sql,tuple);
    }
    public void editTuple(String field, String newValue, String login){
        String sql = "UPDATE login_table SET "+field+" = ? "
                + "WHERE login = ?";
        String[] args = {newValue,login};
        this.executeUpdateStatement(sql,args);
    }
    public void deleteTuple(String login){
        String sql = "DELETE FROM login_table WHERE login = ?";
        String[] args = {login};
        this.executeUpdateStatement(sql,args);
    }
    public String[] getByLogin(String login){
        String sql = "SELECT * " + "FROM login_table WHERE login = ?";
        String[] args = {login};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }
    private String[] parseResultSet(ResultSet rs){
        boolean exists;
        try {
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(!exists)
            return null;
        String[] res = new String[5];
        try {
            res[0] = rs.getString("login");
            res[1] = rs.getString("password");
            res[2] = rs.getString("name");
            res[3] = rs.getString("rank");
            res[4] = rs.getString("status");
            res[5] = rs.getString("division");
            res[6] = rs.getString("type");
        }
        catch (SQLException e){
            System.out.println("Information retrieval error.");
        }
        return res;
    }
}