package Model;


import Controller.UsersController;

import java.util.Observable;


public abstract class User extends Observable {
    private String userName;
    private String password;
    private String name;
    private String rank;
    private String status;
    private String type;
    private UsersController usersController;


    public User(String userName, String password, String name,String rank, String status,String type, UsersController usersController) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.rank = rank;
        this.status=status;
        this.type=type;
        this.usersController=usersController;
    }




    @Override
    public String toString() {
        return "User:  " + "\n"+
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", status='" + status + '\'' +
                ", rank='" + rank + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' ;
    }

    public void createComplaint(String source, String destination, String description,String date){
        Complaint complaint = new Complaint(source, destination, description, date);
        usersController.addComplaint(complaint);
        setChanged();
        notifyObservers(complaint);

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
