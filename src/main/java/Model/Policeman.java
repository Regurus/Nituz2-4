package Model;

import Controller.UsersController;

public class Policeman extends User {
    public Policeman(String userName, String password, String name,String rank, String status,String type, UsersController uc){
        super(userName,password,name,rank,status,"Policeman", uc);
    }
}
