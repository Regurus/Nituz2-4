package Model;

public class Complaint {
    private String source; //username
    private String destination; //username
    private String description;
    private boolean confirm;
    private String date;

    public Complaint(String source, String destination, String description,String date) {
        this.source = source;
        this.destination = destination;
        this.description = description;
        this.confirm = false;
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
