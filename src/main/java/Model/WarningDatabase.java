package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WarningDatabase extends Database {

    public WarningDatabase(){
        super();
        this.tableName = "warning_table";
        String sql = "CREATE TABLE IF NOT EXISTS warning_table (\n"
                + "	complaintID text NOT NULL,\n"
                + "	destination text NOT NULL\n"
                + ");";
        try{
            // create a new table
            Statement stmt = currentConnection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createWarning(Warning warning){
        String[] str = { String.valueOf(warning.getComplaintID()),String.valueOf(warning.getDestination())};
        String sql = "INSERT INTO warning_table (complaintID,destination) VALUES(?,?)";
        this.executeUpdateStatement(sql,str);
    }

    public Warning getWarningByField(String field, String value){
        String sql = "SELECT* FROM warning_table WHERE " + field + " = ? ";
        String[] args = {value};
        ResultSet rs = this.executeGetStatement(sql,args);
        Warning w = null;
        try{
            w = new Warning(Integer.parseInt(rs.getString("complaintID")),
                    rs.getString("destination"));
        }
        catch (SQLException e){
            System.out.println("getWarningByUsername error");
        }
        return w;
    }

    public boolean checkIf3WarningsAndDeleteThem(String username){
        String sql = "SELECT* FROM warning_table WHERE destination = ? ";
        String[] args = {username};
        ResultSet rs = this.executeGetStatement(sql,args);
        int count = 0;
        try{
            while(rs.next())
            {
                count++;
                if(count==3){
                    removeWarnings(username);
                    return true;
                }
            }
        }
        catch (SQLException e){
            System.out.println("checkIf3WarningsAndDeleteThem error");
        }
        return false;
    }

    private void removeWarnings(String username){
        String sql = "DELETE FROM warning_table WHERE destination = ?";
        String[] args = {username};
        this.executeGetStatement(sql,args);
    }


}
