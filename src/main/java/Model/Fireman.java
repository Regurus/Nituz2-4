package Model;

import Controller.UsersController;

public class Fireman extends User{
    public Fireman(String userName, String password, String name,String rank, String status,String type){
        super(userName,password,name,rank,status,"Fireman");
    }
}
