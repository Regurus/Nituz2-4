package Model;

import Controller.EASystem;

import java.util.Observable;
import java.util.Observer;

public class AdminUser implements Observer {
    private String username;
    private String name;
    private String password;
    private String division;
    private EASystem systemController;


    public AdminUser(String username, String name, String password, String division,EASystem systemController) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.division = division;
        this.systemController = systemController;
    }

    public void createCategory(String name){
        systemController.createNewCategory(new Category(name));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
