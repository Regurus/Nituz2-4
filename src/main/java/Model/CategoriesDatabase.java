package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriesDatabase extends Database{

    public CategoriesDatabase(){
        super();
        this.tableName = "categories_table";
        String sql = "CREATE TABLE IF NOT EXISTS categories_table (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	name text NOT NULL\n"
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
    //0-id
    //1-name
    public void createCategory(String name){
        String sql = "INSERT INTO categories_table (name) VALUES(?)";
        String[] tuple={name};
        this.executeUpdateStatement(sql,tuple);
    }

    public boolean isExist(String name){
        String sql = "SELECT* FROM categories_table WHERE name = ?";
        String[] args = {name};
        ResultSet rs = this.executeGetStatement(sql,args);
        try{
            if(!rs.next())
                return false;
        }
        catch (SQLException e){
            System.out.println("Category is Exist error");
        }
        return true;

    }


}