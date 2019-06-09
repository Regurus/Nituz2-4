package Model;

import Controller.UsersController;

public class Dispatcher extends User {
    public Dispatcher(String userName, String password, String name,String rank, String status,String type, UsersController uc){
        super(userName,password,name,rank,status,"Dispature", uc);
    }



}
