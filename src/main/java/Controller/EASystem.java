package Controller;
import Model.CategoriesDatabase;
import Model.Category;
import Model.Complaint;

public class EASystem {
    private static EASystem eaSystem = null;
    private CategoriesDatabase categoriesDB;

    private EASystem(CategoriesDatabase categoriesDB) {
        if(eaSystem == null){
            this.categoriesDB = categoriesDB;
        }
    }

    public static EASystem eaSystemInstance(CategoriesDatabase categoriesDB) {
        if(eaSystem == null)
        {
            eaSystem = new EASystem(categoriesDB);
        }
        return eaSystem;
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
