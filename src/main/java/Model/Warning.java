package Model;

public class Warning {
    private int id;
    private String destination;
    private int complaintID;

    public Warning(int id, String destination, int complaintID) {
        this.id = id;
        this.destination = destination;
        this.complaintID = complaintID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }
}
