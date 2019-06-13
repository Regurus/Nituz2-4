package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDatabase extends Database {
    public AdminDatabase(){
        super("adminDB.db");
        this.tableName = "adminUsers_table";
        String sql = "CREATE TABLE IF NOT EXISTS adminUsers_table (\n"
                + "	username text PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	password text NOT NULL,\n"
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

    public AdminUser getByUsername(String username){
        String sql = "SELECT * " + "FROM adminUsers_table WHERE username = ?";
        String[] args = {username};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }

    public AdminUser getByUsernameAndPassword(String username, String password){
        String sql = "SELECT * " + "FROM adminUsers_table WHERE username = ? AND password = ?";
        String[] args = {username, password};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }

    private AdminUser parseResultSet(ResultSet rs){
        boolean exists;
        try {
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(!exists)
            return null;
        AdminUser a = null;
        try {

                a = new AdminUser(rs.getString("username"), rs.getString("name"),
                    rs.getString("password"), rs.getString("type"));

        }
        catch (SQLException e){
            System.out.println("parseResultSet UserDatabase error.");
        }
        return a;
    }

}
