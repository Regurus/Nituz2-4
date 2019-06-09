package Controller;
import Model.Category;
import Model.Complaint;

public class EASystem {

    static int categoryId = 0;

    public void createNewComplaint(String targetUser,String division,String descr){
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
        System.out.println("inbar");

    }

    public int getAvailableCategoryId(){
        return categoryId;
    }

    /**
     * every time category is created we'll call this func so
     * the next available id would be the value of categoryId
     */
    public void incrementCategoryId(){
        categoryId++;
    }
}
