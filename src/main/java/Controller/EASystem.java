package Controller;

public class EASystem {
    public void createNewComplaint(String targetUser,String division,String descr){
        System.out.println("Complaint Filed:\n To: "+targetUser+"\n From division: "+division+"\n Description: "+descr);
    }
    public void approveComplaint(String id){
        System.out.println("Complaint approved: "+id);

    }
    public void createNewCategory(String name){
        System.out.println("Category created: "+name);

    }
}
