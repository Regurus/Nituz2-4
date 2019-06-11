package Model;

public class Complaint {
    private int id;
    private String source; //username
    private String destination; //username
    private String description;
    private String confirm; //pending/yes/no
    private String date;
    private String type; // devision name

    public Complaint(int id,String source, String destination, String description,String date,String confirm, String type) {
        this.id=id;
        this.source = source;
        this.destination = destination;
        this.description = description;
        this.confirm = confirm;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String toString(){
        return "Complaint id: " + id + ", made by: " + source + ", about: " + destination + ", date: " + date;
    }

}
