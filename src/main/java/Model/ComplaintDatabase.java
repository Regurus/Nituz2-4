package Model;

import java.sql.SQLException;
import java.sql.Statement;

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


}
