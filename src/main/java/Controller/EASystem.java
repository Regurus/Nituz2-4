package Controller;
import Model.CategoriesDatabase;
import Model.Category;
import Model.Complaint;

public class EASystem {

    CategoriesDatabase categoriesDB;

    public EASystem(CategoriesDatabase categoriesDB) {
        this.categoriesDB = categoriesDB;
    }

    public void createNewComplaint(String targetUser, String division, String descr ){
        System.out.println("Complaint Filed:\n To: "+targetUser+"\n From division: "+division+"\n Description: "+descr);
    }
    public void approveComplaint(String id){
        System.out.println("Complaint approved: "+id);
    }
    public Complaint getComplaintById(String id){
        return null;
    }
    public void createNewCategory(Category ctr){
        //add the given category to the DB;
        // write the category addition to logger
        categoriesDB.createCategory(ctr.getName());


    }




}
