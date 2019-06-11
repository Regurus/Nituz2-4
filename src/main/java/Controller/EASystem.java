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

    public static EASystem eaSystemInstance() {
        if(eaSystem == null)
        {
            eaSystem = new EASystem(new CategoriesDatabase());
        }
        return eaSystem;
    }

    public boolean createNewCategory(String category){

        return true;
    }//TODO




}
