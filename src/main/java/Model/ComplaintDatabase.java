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
                + "	id text PRIMARY KEY,\n"
                + "	source text NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	description text NOT NULL,\n"
                + "	confirm text NOT NULL,\n"
                + "	date text NOT NULL,\n"
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
    protected String[] complaintToStringArray(Complaint c){
        String[] str = new String[7];
        str[0] = String.valueOf(c.getId());
        str[1] = c.getSource();
        str[2] = c.getDestination();
        str[3] = c.getDescription();
        str[4] = c.getConfirm();
        str[5] = c.getDate();
        str[6] = c.getType();
        return str;
    }

    public void createComplaint(Complaint c){
        String [] complaint = complaintToStringArray(c);
        String sql = "INSERT INTO complaint_table (id,source,destination,description,confirm,date,type) VALUES(?,?,?,?,?,?,?)";
        this.executeUpdateStatement(sql,complaint);
    }
    public void editTuple(String field, String newValue, int id){
        String sql = "UPDATE complaint_table SET "+field+" = ? "
                + "WHERE id = ?";
        String[] args = {newValue,String.valueOf(id)};
        this.executeUpdateStatement(sql,args);
    }

    public void editConfirmation(String newValue, int id){
        String sql = "UPDATE complaint_table SET confirm = ? "
                + "WHERE id = ?";
        String[] args = {newValue,String.valueOf(id)};
        this.executeUpdateStatement(sql,args);
    }

    public void deleteByID(int id){
        String sql = "DELETE FROM complaint_table WHERE id = ?";
        String[] args = {String.valueOf(id)};
        this.executeUpdateStatement(sql,args);
    }


    public Complaint getByID(int id){
        String sql = "SELECT * " + "FROM complaint_table WHERE id = ?";
        String[] args = {String.valueOf(id)};
        ResultSet rs = this.executeGetStatement(sql,args);
        ArrayList<Complaint> c = parseResultSet(rs);
        return c.get(0);
    }

    public ArrayList<Complaint> getUserComplaints(String source){
        String sql = "SELECT * FROM complaint_table WHERE source = ?";
        String[] args = {source};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }

    public ArrayList<Complaint> getDivisionComplaints(String type){
        String sql = "SELECT * FROM complaint_table WHERE type = ?";
        String[] args = {type};
        ResultSet rs = this.executeGetStatement(sql,args);
        return parseResultSet(rs);
    }

    private ArrayList<Complaint> parseResultSet(ResultSet rs){
        boolean exists;
        try {
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(!exists)
            return null;
        ArrayList <Complaint> complaint = new ArrayList<Complaint>();
        try {
            complaint.add(new Complaint(Integer.parseInt(rs.getString("id")), rs.getString("source"),
                    rs.getString("destination"), rs.getString("description"),
                    rs.getString("confirm"), rs.getString("date"), rs.getString("type")));
            while(rs.next()) {
                complaint.add(new Complaint(Integer.parseInt(rs.getString("id")), rs.getString("source"),
                        rs.getString("destination"), rs.getString("description"),
                        rs.getString("confirm"), rs.getString("date"), rs.getString("type")));
            }
        }
        catch (SQLException e){
            System.out.println("Information retrieval error.");
        }
        return complaint;
    }



}
