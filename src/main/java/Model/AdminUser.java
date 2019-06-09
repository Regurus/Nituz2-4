package Model;

import Controller.EASystem;

public class AdminUser {
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
        Category resCategory = new Category(systemController.getAvailableCategoryId(),name);
        systemController.incrementCategoryId();
        systemController.createNewCategory(resCategory);
    }
}
