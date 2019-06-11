package Model;

import Controller.EASystem;

import java.util.Observable;
import java.util.Observer;

public class AdminUser  {
    private String username;
    private String name;
    private String password;
    private String division;


    public AdminUser(String username, String name, String password, String division) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.division = division;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDivision() {
        return division;
    }

}
