package Model;

public class Warning {
    private String destination;
    private int complaintID;

    public Warning(int complaintID, String destination) {
        this.complaintID = complaintID;
        this.destination = destination;
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
