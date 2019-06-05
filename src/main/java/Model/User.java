package Model;

public class User {
    private String userName;
    private String password;
    private String privateName;
    private String status;
    private String division;
    private String type;
    private int rank;
    private boolean logIn;


    public User(String userName, String password, String birthDate, String privateName,String status, String city,String division,int rank,String type, boolean logIn) {
        this.userName = userName;
        this.password = password;
        this.privateName = privateName;
        this.division = division;
        this.status=status;
        this.rank = rank;
        this.type=type;
        this.logIn= false;
    }

    @Override
    public String toString() {
        return "User:  " + "\n"+
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", privateName=" + privateName +
                ", division='" + division + "\n" +
                ", status='" + status + '\'' +
                ", rank='" + rank + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' ;
    }

    public boolean isLegal() {
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String divisin) {
        this.division = divisin;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setLogIn(boolean logIn) {
        this.logIn = logIn;
    }

    public boolean isLogIn() {
        return logIn;
    }

}
