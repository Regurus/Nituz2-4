package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComplaintDatabase extends Database {

    public ComplaintDatabase(){
        super();
        this.tableName = "complaint_table";
        String sql = "CREATE TABLE IF NOT EXISTS complaint_table (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	source text NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	description text NOT NULL,\n"
                + "	confirm text NOT NULL,\n"
                + "	date text NOT NULL\n"
                + ");";
        try{
            // create a new table
            Statement stmt = currentConnection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createTuple(String[] tuple){
        if(tuple.length!= 5)
            throw new RuntimeException("Incorrect tuple size, cannot index");
        String sql = "INSERT INTO complaint_table (source,password,name,rank,status,type) VALUES(?,?,?,?,?,?)";
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

    public ArrayList<User> getUsers(){
        String sql = "SELECT * " + "FROM users_table ";
        String[] args = null;
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<User> res = new ArrayList<User>();
        try{
            while (rs.next()) {
                res.add(new User(rs.getString("username"), rs.getString("password"),
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
        User user = null;
        try {
            user = new User(rs.getString("username"), rs.getString("password"),
                    rs.getString("name"), rs.getString("rank"),
                    rs.getString("status"), rs.getString("type"));
        }
        catch (SQLException e){
            System.out.println("Information retrieval error.");
        }
        return user;
    }




}
